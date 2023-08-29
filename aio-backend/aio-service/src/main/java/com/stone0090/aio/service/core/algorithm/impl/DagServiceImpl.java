package com.stone0090.aio.service.core.algorithm.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.stone0090.aio.dao.mybatis.entity.*;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDagDOMapper;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.DagService;
import com.stone0090.aio.service.core.algorithm.SvcService;
import com.stone0090.aio.service.enums.*;
import com.stone0090.aio.service.model.service.dag.*;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.DagQueryRequest;
import com.stone0090.aio.service.model.web.request.SvcInvokeRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveBriefRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveDetailRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.DagBriefVO;
import com.stone0090.aio.service.model.web.response.DagDetailVO;
import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service("dagService")
public class DagServiceImpl implements DagService, SvcService {

    @Autowired
    private OperatorDagDOMapper operatorDagDOMapper;
    @Autowired
    private SvcServiceImpl svcServiceImpl;


    @Override
    public PageResult<DagBriefVO> list(DagQueryRequest queryRequest, PageRequest pageRequest) {
        OperatorDagDOExample example = new OperatorDagDOExample();
        OperatorDagDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getDagName())) {
            criteria.andDagNameLike("%" + queryRequest.getDagName() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getDagStatus())) {
            criteria.andDagStatusEqualTo(queryRequest.getDagStatus());
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<OperatorDagDO> result = operatorDagDOMapper.selectByExample(example);
        PageResult<DagBriefVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(Converter::toDagBriefVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public DagDetailVO get(IdRequest request) {
        OperatorDagDO result = getOperatorDagDO(request.getId());
        return result != null ? Converter.toDagDetailVO(result) : null;
    }

    @Override
    public int saveBrief(DagSaveBriefRequest request) {
        OperatorDagDO data = Converter.toOperatorDagDO(request);
        if (data.getId() == null) {
            data.setDagStatus(DagStatusEnum.INIT.name());
            data.setDagData("");
            data.setPublishType(DagPublishTypeEnum.ONLINE_SERVICE.name());
            data.setPublishConfig("");
            return operatorDagDOMapper.insertSelective(data);
        } else {
            OperatorDagDO experimentDO = getOperatorDagDO(request.getId());
            if (experimentDO == null) {
                throw new RuntimeException("修改失败，Dag不存在！");
            }
            if (!experimentDO.getDagStatus().equals(DagStatusEnum.INIT.name())) {
                throw new RuntimeException("修改失败，" + DagStatusEnum.getDescByCode(experimentDO.getDagStatus()) + "的Dag不允许修改！");
            }
            data.setGmtModified(new Date());
            return operatorDagDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int saveDetail(DagSaveDetailRequest request) {
        OperatorDagDO experimentDO = getOperatorDagDO(request.getId());
        if (experimentDO == null) {
            throw new RuntimeException("修改失败，Dag不存在！");
        }
        if (!experimentDO.getDagStatus().equals(DagStatusEnum.INIT.name())) {
            throw new RuntimeException("修改失败，" + DagStatusEnum.getDescByCode(experimentDO.getDagStatus()) + "的Dag不允许修改！");
        }
        OperatorDagDO data = new OperatorDagDO();
        data.setId(request.getId());
        DagData dagData = JSON.parseObject(request.getDagData(), DagData.class);
        data.setDagData(JSON.toJSONString(dagData));
        data.setGmtModified(new Date());
        return operatorDagDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int remove(IdRequest request) {
        OperatorDagDO data = new OperatorDagDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return operatorDagDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int onlineSvc(IdRequest request) {
        OperatorDagDO dagDO = getOperatorDagDO(request.getId());
        if (dagDO == null) {
            throw new RuntimeException("服务上线失败，算子编排不存在！");
        }
        DagData dagData = JSON.parseObject(dagDO.getDagData(), DagData.class);
        ServiceDO serviceDO = buildServiceDO(dagDO, dagData, AlgorithmTypeEnum.DAG);
        return svcServiceImpl.online(serviceDO, () -> {
            DeployInfo deployInfo = new DeployInfo();
            deployInfo.setRequestId(UuidUtil.getUuid());
            deployInfo.setNodes(new ArrayList<>());
            dagData.getNodes().forEach(node -> {
                DeployNode deployNode = new DeployNode();
                deployNode.setNeedDeploy(true);
                deployNode.setLanguage("python");
                deployNode.setNodeId(node.getId());
                deployNode.setAlgoCode(node.getAlgorithmCode());
                deployInfo.getNodes().add(deployNode);
            });
            return deployInfo;
        });
    }

    @Override
    public int offlineSvc(IdRequest request) {
        return 0;
    }

    @Override
    public String invokeSvc(SvcInvokeRequest request) {
        return null;
    }

    public OperatorDagDO getOperatorDagDO(Integer id) {
        OperatorDagDOExample example = new OperatorDagDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        List<OperatorDagDO> result = operatorDagDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }

    public ServiceDO buildServiceDO(OperatorDagDO dagDO, DagData dagData, AlgorithmTypeEnum typeEnum) {
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setSvcUuid(UuidUtil.getUuid());
        serviceDO.setSvcName(dagDO.getDagName());
        serviceDO.setSvcType(typeEnum.name());
        serviceDO.setSvcBizId(dagDO.getId());
        serviceDO.setInvokeType(InvokeTypeEnum.SYNC.name());
        serviceDO.setSvcUrl("");
        serviceDO.setCallbackUrl("");
        serviceDO.setSvcStatus(SvcStatusEnum.OFFLINE.name());
        Pair<String, String> dagParam = buildDagParam(dagData);
        serviceDO.setInputParam(dagParam.getKey());
        serviceDO.setOutputParam(dagParam.getValue());
        return serviceDO;
    }

    private Pair<String, String> buildDagParam(DagData dagData) {
        if (CollectionUtils.isEmpty(Arrays.asList(dagData.getNodes()))) {
            return new Pair<>("", "");
        }
        Map<String, DagPort> inputPortMap = new HashMap<>();
        Map<String, DagPort> outputPortMap = new HashMap<>();
        for (DagNode dagNode : dagData.getNodes()) {
            if (dagNode.getPorts() != null) {
                for (DagPort dagPort : dagNode.getPorts()) {
                    if (dagPort.getType().equals(DagPortTypeEnum.input.name())) {
                        inputPortMap.put(dagPort.getId(), dagPort);
                    } else {
                        outputPortMap.put(dagPort.getId(), dagPort);
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(Arrays.asList(dagData.getEdges()))) {
            for (DagEdge dagEdge : dagData.getEdges()) {
                inputPortMap.remove(dagEdge.getTargetPortId());
                outputPortMap.remove(dagEdge.getSourcePortId());
            }
        }
        Map<String, Object> inputParamMap = buildParam(inputPortMap);
        Map<String, Object> outputParamMap = buildParam(outputPortMap);
        return new Pair<>(JSON.toJSONString(inputParamMap), JSON.toJSONString(outputParamMap));
    }

    private Map<String, Object> buildParam(Map<String, DagPort> portMap) {
        JSONObject paramMap = new JSONObject();
        for (Map.Entry<String, DagPort> entry : portMap.entrySet()) {
            DagPort dagPort = entry.getValue();
            JSONObject param = new JSONObject();
            param.put("name", dagPort.getOpParamName());
            param.put("type", dagPort.getOpParamType());
            param.put("required", dagPort.getOpParamRequired());
            paramMap.put(dagPort.getOpParamCode(), param);
        }
        return paramMap;
    }

}
