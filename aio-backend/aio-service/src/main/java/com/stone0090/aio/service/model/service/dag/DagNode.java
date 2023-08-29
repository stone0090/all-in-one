package com.stone0090.aio.service.model.service.dag;

import lombok.Data;

@Data
public class DagNode {
    /**
     * 节点的唯一标识
     */
    private String id;
    /**
     * 节点在画布的位置: x
     */
    private Integer x;
    /**
     * 节点在画布的位置: y
     */
    private Integer y;
    /**
     * 节点的渲染宽度
     */
    private Integer width;
    /**
     * 节点的渲染高度
     */
    private Integer height;
    /**
     * 节点名
     */
    private String label;
    /**
     * 节点React组件的key
     */
    private String renderKey;
    /**
     * 是否是Group
     */
    private Boolean isGroup;
    /**
     * 所属群组
     */
    private String group;
    /**
     * 节点的锚点
     */
    private DagPort[] ports;
    /**
     * 算子标识
     */
    private String opCode;
    /**
     * 算子名称
     */
    private String opName;
    /**
     * 编程语言
     */
    private String programmingLanguage;
    /**
     * 算法代码
     */
    private String algorithmCode;
    /**
     * 算法路径
     */
    private String algorithmPath;
}


