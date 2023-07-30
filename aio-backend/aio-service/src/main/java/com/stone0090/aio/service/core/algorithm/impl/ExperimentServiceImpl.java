package com.stone0090.aio.service.core.algorithm.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.dao.mybatis.entity.*;
import com.stone0090.aio.dao.mybatis.mapper.ExperimentDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.ExperimentService;
import com.stone0090.aio.service.enums.*;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.ExperimentQueryRequest;
import com.stone0090.aio.service.model.web.request.ExperimentSaveBriefRequest;
import com.stone0090.aio.service.model.web.request.ExperimentSaveDetailRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.ExperimentBriefVO;
import com.stone0090.aio.service.model.web.response.ExperimentDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    private ExperimentDOMapper experimentDOMapper;

    @Override
    public PageResult<ExperimentBriefVO> list(ExperimentQueryRequest queryRequest, PageRequest pageRequest) {
        ExperimentDOExample example = new ExperimentDOExample();
        ExperimentDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getExName())) {
            criteria.andExNameLike("%" + queryRequest.getExName() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getExStatus())) {
            criteria.andExStatusEqualTo(queryRequest.getExStatus());
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<ExperimentDO> result = experimentDOMapper.selectByExample(example);
//        List<ApiDO> apiDOList = apiServiceImpl.listApiByTypeIds(AlgoTypeEnum.EXPERIMENT.name(),
//                result.stream().map(OperatorDO::getId).collect(Collectors.toList()));
//        Map<Integer, ApiDO> apiMap = apiDOList.stream().collect(Collectors.toMap(ApiDO::getTypeId, apiDO -> apiDO));
        PageResult<ExperimentBriefVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(Converter::toExperimentBriefVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public ExperimentDetailVO get(IdRequest request) {
        ExperimentDO result = getExperimentDO(request.getId());
        return result != null ? Converter.toExperimentDetailVO(result) : null;
    }

    @Override
    public int saveBrief(ExperimentSaveBriefRequest request) {
        ExperimentDO data = Converter.toExperimentDO(request);
        if (data.getId() == null) {
            data.setExStatus(ExStatusEnum.INIT.name());
            data.setNodeDag("");
            data.setPublishType(ExPublishTypeEnum.SECOND_SCHEDULING.name());
            data.setPublishConfig("");
            return experimentDOMapper.insertSelective(data);
        } else {
            ExperimentDO experimentDO = getExperimentDO(request.getId());
            if (experimentDO == null) {
                throw new RuntimeException("修改失败，画布不存在！");
            }
            if (!experimentDO.getExStatus().equals(ExStatusEnum.INIT.name())) {
                throw new RuntimeException("修改失败，" + ExStatusEnum.getDescByCode(experimentDO.getExStatus()) + "的画布不允许修改！");
            }
            data.setGmtModified(new Date());
            return experimentDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int saveDetail(ExperimentSaveDetailRequest request) {
        return 0;
    }

    @Override
    public int remove(IdRequest request) {
//        ApiDO apiDO = apiServiceImpl.getApiByTypeId(AlgoTypeEnum.OPERATOR.name(), request.getId());
//        if (apiDO != null) {
//            if (ApiStatusEnum.ONLINE.name().equals(apiDO.getApiStatus())) {
//                throw new RuntimeException("算子删除失败，API状态为在线的算子不可删除！");
//            }
//            apiServiceImpl.removeApiByTypeId(AlgoTypeEnum.OPERATOR.name(), request.getId());
//        }
        ExperimentDO data = new ExperimentDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return experimentDOMapper.updateByPrimaryKeySelective(data);
    }

    public ExperimentDO getExperimentDO(Integer id) {
        ExperimentDOExample example = new ExperimentDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        List<ExperimentDO> result = experimentDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }

}
