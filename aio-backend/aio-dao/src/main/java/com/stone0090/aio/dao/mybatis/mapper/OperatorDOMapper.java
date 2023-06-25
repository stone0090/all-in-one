package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.OperatorDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperatorDOMapper {
    int countByExample(OperatorDOExample example);

    int deleteByExample(OperatorDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperatorDO record);

    int insertSelective(OperatorDO record);

    List<OperatorDO> selectByExample(OperatorDOExample example);

    OperatorDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperatorDO record, @Param("example") OperatorDOExample example);

    int updateByExample(@Param("record") OperatorDO record, @Param("example") OperatorDOExample example);

    int updateByPrimaryKeySelective(OperatorDO record);

    int updateByPrimaryKey(OperatorDO record);
}