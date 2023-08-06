package com.stone0090.aio.service.core.algorithm.impl;

import com.github.pagehelper.PageHelper;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDOExample;
import com.stone0090.aio.dao.mybatis.mapper.OperatorDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.algorithm.DagService;
import com.stone0090.aio.service.enums.AlgoTypeEnum;
import com.stone0090.aio.service.enums.OpStatusEnum;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.response.OperatorAndGroupVO;
import com.stone0090.aio.service.model.web.response.OperatorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author stone
 * @date 2023/08/05
 */
@Service
public class DagServiceImpl implements DagService {

    @Autowired
    private OperatorDOMapper operatorDOMapper;

    @Override
    public List<OperatorAndGroupVO> listOpGroups() {
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
}
