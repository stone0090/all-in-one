package com.stone0090.aio.service.core.algorithm.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.dao.mybatis.entity.OperatorDagDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDagDOExample;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDagDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.OperatorDagService;
import com.stone0090.aio.service.enums.DagPublishTypeEnum;
import com.stone0090.aio.service.enums.DagStatusEnum;
import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.DagQueryRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveBriefRequest;
import com.stone0090.aio.service.model.web.request.save.DagSaveDetailRequest;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.response.DagBriefVO;
import com.stone0090.aio.service.model.web.response.DagDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorDagServiceImpl implements OperatorDagService {

    @Autowired
    private OperatorDagDOMapper operatorDagDOMapper;

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
            data.setDagNodes("");
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
        OperatorDagDO data = Converter.toOperatorDagDO(request);
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

    @Override
    public int remove(IdRequest request) {
        OperatorDagDO data = new OperatorDagDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return operatorDagDOMapper.updateByPrimaryKeySelective(data);
    }

    public OperatorDagDO getOperatorDagDO(Integer id) {
        OperatorDagDOExample example = new OperatorDagDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(id);
        List<OperatorDagDO> result = operatorDagDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }

}
