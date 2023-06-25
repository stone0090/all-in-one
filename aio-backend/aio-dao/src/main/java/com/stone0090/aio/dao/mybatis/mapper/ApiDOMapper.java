package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.ApiDO;
import com.stone0090.aio.dao.mybatis.entity.ApiDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiDOMapper {
    int countByExample(ApiDOExample example);

    int deleteByExample(ApiDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApiDO record);

    int insertSelective(ApiDO record);

    List<ApiDO> selectByExample(ApiDOExample example);

    ApiDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApiDO record, @Param("example") ApiDOExample example);

    int updateByExample(@Param("record") ApiDO record, @Param("example") ApiDOExample example);

    int updateByPrimaryKeySelective(ApiDO record);

    int updateByPrimaryKey(ApiDO record);
}