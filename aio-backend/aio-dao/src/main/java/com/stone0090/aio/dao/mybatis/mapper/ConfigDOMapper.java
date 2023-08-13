package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.ConfigDO;
import com.stone0090.aio.dao.mybatis.entity.ConfigDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigDOMapper {
    int countByExample(ConfigDOExample example);

    int deleteByExample(ConfigDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigDO record);

    int insertSelective(ConfigDO record);

    List<ConfigDO> selectByExample(ConfigDOExample example);

    ConfigDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigDO record, @Param("example") ConfigDOExample example);

    int updateByExample(@Param("record") ConfigDO record, @Param("example") ConfigDOExample example);

    int updateByPrimaryKeySelective(ConfigDO record);

    int updateByPrimaryKey(ConfigDO record);
}