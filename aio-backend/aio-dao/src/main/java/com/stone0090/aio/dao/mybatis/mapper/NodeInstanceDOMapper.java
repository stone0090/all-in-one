package com.stone0090.aio.dao.mybatis.mapper;

import com.stone0090.aio.dao.mybatis.entity.NodeInstanceDO;
import com.stone0090.aio.dao.mybatis.entity.NodeInstanceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeInstanceDOMapper {
    int countByExample(NodeInstanceDOExample example);

    int deleteByExample(NodeInstanceDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NodeInstanceDO record);

    int insertSelective(NodeInstanceDO record);

    List<NodeInstanceDO> selectByExample(NodeInstanceDOExample example);

    NodeInstanceDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NodeInstanceDO record, @Param("example") NodeInstanceDOExample example);

    int updateByExample(@Param("record") NodeInstanceDO record, @Param("example") NodeInstanceDOExample example);

    int updateByPrimaryKeySelective(NodeInstanceDO record);

    int updateByPrimaryKey(NodeInstanceDO record);
}