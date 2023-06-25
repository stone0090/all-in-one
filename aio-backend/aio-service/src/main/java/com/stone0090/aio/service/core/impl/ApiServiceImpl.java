package com.stone0090.aio.service.core.impl;

import com.stone0090.aio.api.protocal.PageRequest;
import com.stone0090.aio.api.protocal.PageResult;
import com.stone0090.aio.api.request.ApiQueryRequest;
import com.stone0090.aio.api.request.ApiSaveRequest;
import com.stone0090.aio.api.request.IdRequest;
import com.stone0090.aio.api.response.ApiVO;
import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.ApiDOExample;
import com.stone0090.aio.dao.mybatis.mapper.ApiDOMapper;
import com.stone0090.aio.service.common.Converter;
import com.stone0090.aio.service.core.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDOMapper apiDOMapper;

    @Override
    public PageResult<ApiVO> list(ApiQueryRequest queryRequest, PageRequest pageRequest) {
        return null;
    }

    @Override
    public ApiVO get(IdRequest request) {
        return null;
    }

    @Override
    public int save(ApiSaveRequest request) {
        ApiDO data = Converter.toApiDO(request);
        return save(data);
    }

    @Override
    public int remove(IdRequest request) {
        return 0;
    }

    public int save(ApiDO data) {
        if (data.getId() == null) {
            return apiDOMapper.insertSelective(data);
        } else {
            data.setGmtModified(new Date());
            return apiDOMapper.updateByPrimaryKeySelective(data);
        }
    }

    public ApiDO getByType(String type, Integer typeId) {
        ApiDOExample example = new ApiDOExample();
        ApiDOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(0);
        criteria.andApiTypeEqualTo(type);
        criteria.andTypeIdEqualTo(typeId);
        List<ApiDO> result = apiDOMapper.selectByExample(example);
        return result.size() > 0 ? result.get(0) : null;
    }
}
