package com.stone0090.aio.service.model.service.dag;

import lombok.Data;

@Data
public class DagEdge {
    /**
     * 边的唯一标识
     */
    private String id;
    /**
     * 边的上游节点id 和连接桩
     */
    private String source;
    /**
     * 边的下游节点id 和连接桩
     */
    private String target;
    /**
     * 边的上游节点的锚点Id
     */
    private String sourcePortId;
    /**
     * 边的下游节点的锚点Id
     */
    private String targetPortId;
    /**
     * 边上label
     */
    private String label;
    /**
     * 边上渲染React组件的key
     */
    private String renderKey;
    /**
     * 边上渲染内容的宽度
     */
    private Integer edgeContentWidth;
    /**
     * 边上渲染内容的高度
     */
    private Integer edgeContentHeight;
}


