package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.OperatorNodeDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorNodeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperatorNodeDOMapper {
    int countByExample(OperatorNodeDOExample example);

    int deleteByExample(OperatorNodeDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperatorNodeDO record);

    int insertSelective(OperatorNodeDO record);

    List<OperatorNodeDO> selectByExample(OperatorNodeDOExample example);

    OperatorNodeDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperatorNodeDO record, @Param("example") OperatorNodeDOExample example);

    int updateByExample(@Param("record") OperatorNodeDO record, @Param("example") OperatorNodeDOExample example);

    int updateByPrimaryKeySelective(OperatorNodeDO record);

    int updateByPrimaryKey(OperatorNodeDO record);
}