package com.stone0090.aio.service.core.system.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.stone0090.aio.service.model.web.protocal.PageRequest;
import com.stone0090.aio.service.model.web.protocal.PageResult;
import com.stone0090.aio.service.model.web.request.IdRequest;
import com.stone0090.aio.service.model.web.request.PermissionQueryRequest;
import com.stone0090.aio.service.model.web.request.PermissionSaveRequest;
import com.stone0090.aio.service.model.web.response.PermissionVO;
import com.stone0090.aio.dao.mybatis.entity.PermissionDO;
import com.stone0090.aio.dao.mybatis.entity.PermissionDOExample;
import com.stone0090.aio.dao.mybatis.entity.PermissionDOExample.Criteria;
import com.stone0090.aio.dao.mybatis.entity.RolePermissionRelationDO;
import com.stone0090.aio.dao.mybatis.entity.RolePermissionRelationDOExample;
import com.stone0090.aio.dao.mybatis.mapper.PermissionDOMapper;
import com.stone0090.aio.dao.mybatis.mapper.RolePermissionRelationDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.github.pagehelper.PageHelper;
import com.stone0090.aio.service.core.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author stone
 * @date 2021/08/03
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDOMapper permissionDOMapper;
    @Autowired
    private RolePermissionRelationDOMapper rolePermissionRelationDOMapper;

    @Override
    public PageResult<PermissionVO> list(PermissionQueryRequest queryRequest, PageRequest pageRequest) {
        PermissionDOExample example = new PermissionDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        if (!StringUtils.isEmpty(queryRequest.getPermissionCode())) {
            criteria.andPermissionCodeLike("%" + queryRequest.getPermissionCode() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getPermissionName())) {
            criteria.andPermissionNameLike("%" + queryRequest.getPermissionName() + "%");
        }
        if (!StringUtils.isEmpty(queryRequest.getPermissionUrl())) {
            criteria.andPermissionUrlLike("%" + queryRequest.getPermissionUrl() + "%");
        }
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<PermissionDO> result = permissionDOMapper.selectByExample(example);
        PageResult<PermissionVO> pageResult = PageResult.buildPageResult(result);
        pageResult.setList(result.stream().map(Converter::toPermissionVO).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public PermissionVO get(IdRequest request) {
        PermissionDOExample example = new PermissionDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andIdEqualTo(request.getId());
        List<PermissionDO> result = permissionDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : Converter.toPermissionVO(result.get(0));
    }

    @Override
    public int save(PermissionSaveRequest request) {
        PermissionDO data = Converter.toPermissionDO(request);
        if (data.getId() == null) {
            return permissionDOMapper.insertSelective(data);
        } else {
            data.setPermissionCode(null);
            data.setGmtModified(new Date());
            return permissionDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    @Override
    public int remove(IdRequest request) {
        PermissionDO data = new PermissionDO();
        data.setId(request.getId());
        data.setIsDeleted((int)System.currentTimeMillis());
        data.setGmtModified(new Date());
        return permissionDOMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public Map<String, List<PermissionVO>> listByRoleCodes(List<String> roleCodeList) {
        RolePermissionRelationDOExample example = new RolePermissionRelationDOExample();
        example.createCriteria().andIsDeletedEqualTo(0).andRoleCodeIn(roleCodeList);
        List<RolePermissionRelationDO> relationDOList = rolePermissionRelationDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(relationDOList)) {
            return Collections.emptyMap();
        }
        List<String> permissionCodeList = relationDOList.stream().map(RolePermissionRelationDO::getPermissionCode)
            .collect(Collectors.toList());
        PermissionDOExample permissionDOExample = new PermissionDOExample();
        permissionDOExample.createCriteria().andIsDeletedEqualTo(0).andPermissionCodeIn(permissionCodeList);
        List<PermissionDO> permissionDOList = permissionDOMapper.selectByExample(permissionDOExample);
        if (CollectionUtils.isEmpty(permissionDOList)) {
            return Collections.emptyMap();
        }
        Map<String, PermissionDO> permissionDOMap = permissionDOList.stream()
            .collect(Collectors.toMap(PermissionDO::getPermissionCode, Function.identity(), (x, y) -> x));
        Map<String, List<PermissionVO>> roleCodePermissionVOMap = relationDOList.stream()
            .collect(Collectors.groupingBy(RolePermissionRelationDO::getRoleCode, Collectors.mapping(
                x -> Converter.toPermissionVO(permissionDOMap.get(x.getPermissionCode())), Collectors.toList())));
        return roleCodePermissionVOMap;
    }

}
