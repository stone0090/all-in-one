package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.OperatorDagDO;
import com.stone0090.aio.dao.mybatis.entity.OperatorDagDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperatorDagDOMapper {
    int countByExample(OperatorDagDOExample example);

    int deleteByExample(OperatorDagDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperatorDagDO record);

    int insertSelective(OperatorDagDO record);

    List<OperatorDagDO> selectByExample(OperatorDagDOExample example);

    OperatorDagDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperatorDagDO record, @Param("example") OperatorDagDOExample example);

    int updateByExample(@Param("record") OperatorDagDO record, @Param("example") OperatorDagDOExample example);

    int updateByPrimaryKeySelective(OperatorDagDO record);

    int updateByPrimaryKey(OperatorDagDO record);
}