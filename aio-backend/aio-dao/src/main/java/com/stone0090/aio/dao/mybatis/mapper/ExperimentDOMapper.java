package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.ExperimentDO;
import com.stone0090.aio.dao.mybatis.entity.ExperimentDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExperimentDOMapper {
    int countByExample(ExperimentDOExample example);

    int deleteByExample(ExperimentDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExperimentDO record);

    int insertSelective(ExperimentDO record);

    List<ExperimentDO> selectByExample(ExperimentDOExample example);

    ExperimentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExperimentDO record, @Param("example") ExperimentDOExample example);

    int updateByExample(@Param("record") ExperimentDO record, @Param("example") ExperimentDOExample example);

    int updateByPrimaryKeySelective(ExperimentDO record);

    int updateByPrimaryKey(ExperimentDO record);
}