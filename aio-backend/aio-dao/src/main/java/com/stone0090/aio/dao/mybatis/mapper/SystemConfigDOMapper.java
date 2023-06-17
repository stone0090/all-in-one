package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.SystemConfigDO;
import com.stone0090.aio.dao.mybatis.entity.SystemConfigDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemConfigDOMapper {
    int countByExample(SystemConfigDOExample example);

    int deleteByExample(SystemConfigDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemConfigDO record);

    int insertSelective(SystemConfigDO record);

    List<SystemConfigDO> selectByExample(SystemConfigDOExample example);

    SystemConfigDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemConfigDO record, @Param("example") SystemConfigDOExample example);

    int updateByExample(@Param("record") SystemConfigDO record, @Param("example") SystemConfigDOExample example);

    int updateByPrimaryKeySelective(SystemConfigDO record);

    int updateByPrimaryKey(SystemConfigDO record);
}