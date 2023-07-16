package com.stone0090.aio.service.core.system.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.UserQueryRequest;
import com.stone0090.aio.service.model.web.request.UserSaveRequest;
import com.stone0090.aio.service.model.web.response.UserBriefVO;
import com.stone0090.aio.service.model.web.response.UserDetailVO;
import com.stone0090.aio.dao.mybatis.entity.UserDO;
import com.stone0090.aio.dao.mybatis.entity.UserDOExample;
import com.stone0090.aio.dao.mybatis.entity.UserDOExample.Criteria;
import com.stone0090.aio.dao.mybatis.mapper.UserDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.github.pagehelper.PageHelper;
import com.stone0090.aio.service.core.system.RoleService;
import com.stone0090.aio.service.core.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public PageResult<UserBriefVO> list(UserQueryRequest queryRequest, PageRequest pageRequest) {
        UserDOExample example = new UserDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getUsername())) {
            criteria.andUsernameLike("%" + queryRequest.getUsername() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getNickname())) {
            criteria.andNicknameLike("%" + queryRequest.getNickname() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<UserDO> result = userDOMapper.selectByExample(example);
        PageResult<UserBriefVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(userDO -> {
            UserBriefVO userBriefVO = Converter.toBriefVO(userDO);
            userBriefVO.setRoles(roleService.listByUsername(userDO.getUsername()));
            return userBriefVO;
        }).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public UserDetailVO getDetail(IdRequest request) {
        UserDO result = getById(request.getId());
        if (result == null) {
            return null;
        }
        UserDetailVO userDetailVO = Converter.toDetailVO(result);
        userDetailVO.setRoles(roleService.listByUsername(userDetailVO.getUsername()));
        return userDetailVO;
    }

    @Override
    public UserDetailVO getDetail(String username) {
        UserDO result = getByUsername(username);
        if (result == null) {
            return null;
        }
        UserDetailVO userDetailVO = Converter.toDetailVO(result);
        userDetailVO.setRoles(roleService.listByUsername(userDetailVO.getUsername()));
        return userDetailVO;
    }

    @Override
    public int add(UserSaveRequest request) {
        if (countByUsername(request.getUsername()) > 0) {
            throw new RuntimeException("username已存在！");
        }
        UserDO userDO = Converter.toUserDO(request);
        // 新增时不允许指定id，只能自增
        userDO.setId(null);
        return userDOMapper.insertSelective(userDO);
    }

    @Override
    public int edit(UserSaveRequest request) {
        UserDO data = Converter.toUserDO(request);
        // 不允许修改业务唯一标识
        data.setUsername(null);
        data.setGmtModified(new Date());
        return userDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int remove(IdRequest request) {
        UserDO data = new UserDO();
        data.setId(request.getId());
        data.setIsDeleted((int) System.currentTimeMillis());
        data.setGmtModified(new Date());
        return userDOMapper.updateByPrimaryKeySelective(data);
    }

    private int countByUsername(String username) {
        UserDO condition = new UserDO();
        condition.setUsername(username);
        UserDOExample example = buildCondition(condition);
        return userDOMapper.countByExample(example);
    }

    private UserDO getByUsername(String username) {
        UserDO condition = new UserDO();
        condition.setUsername(username);
        return getByCondition(buildCondition(condition));
    }

    private UserDO getById(Integer id) {
        UserDO condition = new UserDO();
        condition.setId(id);
        return getByCondition(buildCondition(condition));
    }

    private UserDO getByCondition(UserDOExample example) {
        List<UserDO> result = userDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    private UserDOExample buildCondition(UserDO userDO) {
        UserDOExample example = new UserDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (userDO.getId() != null && userDO.getId() > 0) {
            criteria.andIdEqualTo(userDO.getId());
        }
        if (!StringUtils.isEmpty(userDO.getUsername())) {
            criteria.andUsernameEqualTo(userDO.getUsername());
        }
        return example;
    }

}
