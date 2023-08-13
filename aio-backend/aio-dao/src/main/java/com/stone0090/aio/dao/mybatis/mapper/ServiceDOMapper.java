package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.ServiceDO;
import com.stone0090.aio.dao.mybatis.entity.ServiceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceDOMapper {
    int countByExample(ServiceDOExample example);

    int deleteByExample(ServiceDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceDO record);

    int insertSelective(ServiceDO record);

    List<ServiceDO> selectByExample(ServiceDOExample example);

    ServiceDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceDO record, @Param("example") ServiceDOExample example);

    int updateByExample(@Param("record") ServiceDO record, @Param("example") ServiceDOExample example);

    int updateByPrimaryKeySelective(ServiceDO record);

    int updateByPrimaryKey(ServiceDO record);
}