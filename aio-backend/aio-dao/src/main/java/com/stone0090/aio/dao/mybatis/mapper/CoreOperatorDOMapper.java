package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.CoreOperatorDO;
import com.stone0090.aio.dao.mybatis.entity.CoreOperatorDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoreOperatorDOMapper {
    int countByExample(CoreOperatorDOExample example);

    int deleteByExample(CoreOperatorDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoreOperatorDO record);

    int insertSelective(CoreOperatorDO record);

    List<CoreOperatorDO> selectByExample(CoreOperatorDOExample example);

    CoreOperatorDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoreOperatorDO record, @Param("example") CoreOperatorDOExample example);

    int updateByExample(@Param("record") CoreOperatorDO record, @Param("example") CoreOperatorDOExample example);

    int updateByPrimaryKeySelective(CoreOperatorDO record);

    int updateByPrimaryKey(CoreOperatorDO record);
}