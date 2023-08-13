package com.stone0090.aio.service.core.algorithm.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.dao.mybatis.entity.OperatorDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDOExample;
import com.stone0090.aio.dao.mybatis.entity.ServiceDO;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDOMapper;
import com.stone0090.aio.manager.utils.UuidUtil;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.SvcService;
import com.stone0090.aio.service.core.algorithm.OperatorService;
import com.stone0090.aio.service.enums.*;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.OperatorQueryRequest;
import com.stone0090.aio.service.model.web.request.SvcInvokeRequest;
import com.stone0090.aio.service.model.web.request.SvcRequest;
import com.stone0090.aio.service.model.web.request.save.OperatorSaveRequest;
import com.stone0090.aio.service.model.web.response.OperatorAndGroupVO;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author stone
 * @date 2023/06/22
 */
@Service("operatorService")
public class OperatorServiceImpl implements OperatorService, SvcService {

    @Autowired
    private OperatorDOMapper operatorDOMapper;
    @Autowired
    private SvcServiceImpl svcServiceImpl;

    @Override
    public PageResult<OperatorVO> list(OperatorQueryRequest queryRequest, PageRequest pageRequest) {
        OperatorDOExample example = new OperatorDOExample();
        OperatorDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getOpCode())) {
            criteria.andOpCodeLike("%" + queryRequest.getOpCode() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getOpName())) {
            criteria.andOpNameLike("%" + queryRequest.getOpName() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        List<ServiceDO> serviceDOList = svcServiceImpl.listServiceByBizIds(AlgorithmTypeEnum.OPERATOR.name(),
                result.stream().map(OperatorDO::getId).collect(Collectors.toList()));
        Map<Integer, ServiceDO> serviceMap = serviceDOList.stream().collect(Collectors.toMap(ServiceDO::getSvcBizId,
                serviceDO -> serviceDO));
        PageResult<OperatorVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(operatorDO -> Converter.toOperatorVO(operatorDO, serviceMap))
                .collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public OperatorVO get(IdRequest request) {
        OperatorDO result = getOperatorDO(request.getId());
        return result != null ? Converter.toOperatorVO(result, null) : null;
    }

    @Override
    public int save(OperatorSaveRequest request) {
        checkSaveParam(request.getInputParam(), true);
        checkSaveParam(request.getOutputParam(), false);
        OperatorDO data = Converter.toOperatorDO(request);
        if (data.getId() == null) {
            data.setAlgorithmPath("");
            data.setOpStatus(OpStatusEnum.INIT.name());
            return operatorDOMapper.insertSelective(data);
        } else {
            OperatorDO operatorDO = getOperatorDO(request.getId());
            if (operatorDO == null) {
                throw new RuntimeException("修改失败，算子不存在！");
            }
            if (!operatorDO.getOpStatus().equals(OpStatusEnum.INIT.name())) {
                throw new RuntimeException("修改失败，" + OpStatusEnum.getDescByCode(operatorDO.getOpStatus()) + "的算子不允许修改！");
            }
            data.setGmtModified(new Date());
            return operatorDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int publish(IdRequest request) {
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("上架失败，算子不存在！");
        }
        if (!operatorDO.getOpStatus().equals(OpStatusEnum.INIT.name())) {
            throw new RuntimeException("上架失败，" + OpStatusEnum.getDescByCode(operatorDO.getOpStatus()) + "的算子不允许上架！");
        }
        OperatorDO data = new OperatorDO();
        data.setId(request.getId());
        data.setOpStatus(OpStatusEnum.PUBLISHED.name());
        data.setGmtModified(new Date());
        return operatorDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int deprecate(IdRequest request) {
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("废弃失败，算子不存在！");
        }
        if (!operatorDO.getOpStatus().equals(OpStatusEnum.PUBLISHED.name())) {
            throw new RuntimeException("废弃失败，" + OpStatusEnum.getDescByCode(operatorDO.getOpStatus()) + "的算子不允许废弃！");
        }
        OperatorDO data = new OperatorDO();
        data.setId(request.getId());
        data.setOpStatus(OpStatusEnum.DEPRECATED.name());
        data.setGmtModified(new Date());
        return operatorDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(IdRequest request) {
        ServiceDO serviceDO = svcServiceImpl.getServiceByBizId(AlgorithmTypeEnum.OPERATOR.name(), request.getId());
        if (serviceDO != null) {
            if (SvcStatusEnum.ONLINE.name().equals(serviceDO.getSvcStatus())) {
                throw new RuntimeException("算子删除失败，服务器状态为在线的算子不可删除！");
            }
            svcServiceImpl.removeServiceByBizId(AlgorithmTypeEnum.OPERATOR.name(), request.getId());
        }
        OperatorDO data = new OperatorDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return operatorDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int onlineSvc(SvcRequest request) {
        if (!AlgorithmTypeEnum.OPERATOR.name().equals(request.getSvcType())) {
            throw new RuntimeException("服务上线失败，类型不正确！");
        }
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("服务上线失败，算子不存在！");
        }
        ServiceDO serviceDO = buildServiceDO(operatorDO, AlgorithmTypeEnum.OPERATOR);
        return svcServiceImpl.online(serviceDO, (deployInfo) -> {
            deployInfo.put("algoCode", operatorDO.getAlgorithmCode());
        });
    }

    @Override
    public int offlineSvc(SvcRequest request) {
        if (!AlgorithmTypeEnum.OPERATOR.name().equals(request.getSvcType())) {
            throw new RuntimeException("服务下线失败，类型不正确！");
        }
        return svcServiceImpl.offline(AlgorithmTypeEnum.OPERATOR.name(), request.getId());
    }

    @Override
    public String invokeSvc(SvcInvokeRequest request) {
        if (!AlgorithmTypeEnum.OPERATOR.name().equals(request.getSvcType())) {
            throw new RuntimeException("服务调用失败，类型不正确！");
        }
        OperatorDO operatorDO = getOperatorDO(request.getId());
        if (operatorDO == null) {
            throw new RuntimeException("服务调用失败，算子不存在！");
        }
        return svcServiceImpl.invoke(AlgorithmTypeEnum.OPERATOR.name(), request.getId(), request.getInputParam());
    }

    @Override
    public List<OperatorAndGroupVO> listDagOpGroups() {
        List<OperatorAndGroupVO> result = new ArrayList<>();
        OperatorDOExample example = new OperatorDOExample();
        OperatorDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0)
                .andOpStatusEqualTo(OpStatusEnum.PUBLISHED.name());
        List<OperatorDO> OperatorDOList = operatorDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(OperatorDOList)) {
            return result;
        }
        OperatorAndGroupVO operatorAndGroupVO = new OperatorAndGroupVO();
        operatorAndGroupVO.setGroupId(1);
        operatorAndGroupVO.setGroupName("默认分组");
        operatorAndGroupVO.setOperatorList(OperatorDOList.stream().map(Converter::toOperatorVO).collect(Collectors.toList()));
        result.add(operatorAndGroupVO);
        return result;
    }

    public OperatorDO getOperatorDO(Integer id) {
        OperatorDOExample example = new OperatorDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        List<OperatorDO> result = operatorDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }

    private void checkSaveParam(String param, boolean isInput) {
        String paramType = isInput ? "入参" : "出参";
        try {
            Map<String, Object> inputParamMap = JsonUtil.parseJson(param);
            inputParamMap.forEach((k, v) -> {
                Map<String, Object> inputParamDetailMap = (Map<String, Object>) v;
                if (inputParamDetailMap.get("name") == null) {
                    throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[name]不能为空！");
                } else {
                    if (!(inputParamDetailMap.get("name") instanceof String)) {
                        throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[name]必须为字符串！");
                    }
                }
                if (inputParamDetailMap.get("type") == null) {
                    throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[type]不能为空！");
                } else {
                    String type = inputParamDetailMap.get("type").toString();
                    if (DataTypeEnum.getByCode(type) == null) {
                        throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[type]只能是\"int/double/string/boolean\"其中一个！");
                    }
                }
                if (isInput) {
                    if (inputParamDetailMap.get("required") == null) {
                        throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[required]不能为空！");
                    } else {
                        if (!(inputParamDetailMap.get("required") instanceof Boolean)) {
                            throw new RuntimeException("算子保存失败，算子" + paramType + "[" + k + "]的格式有误，属性[required]只能是true/false！");
                        }
                    }
                }
            });
        } catch (JoseException e) {
            throw new RuntimeException("算子保存失败，算子" + paramType + "格式有误！");
        }
    }

    public ServiceDO buildServiceDO(OperatorDO operatorDO, AlgorithmTypeEnum typeEnum) {
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setSvcUuid(UuidUtil.getUuid());
        serviceDO.setSvcName(operatorDO.getOpName());
        serviceDO.setSvcType(typeEnum.name());
        serviceDO.setSvcBizId(operatorDO.getId());
        serviceDO.setInputParam(operatorDO.getInputParam());
        serviceDO.setOutputParam(operatorDO.getOutputParam());
        serviceDO.setInvokeType(InvokeTypeEnum.SYNC.name());
        serviceDO.setSvcUrl("");
        serviceDO.setCallbackUrl("");
        serviceDO.setSvcStatus(SvcStatusEnum.OFFLINE.name());
        return serviceDO;
    }

}
