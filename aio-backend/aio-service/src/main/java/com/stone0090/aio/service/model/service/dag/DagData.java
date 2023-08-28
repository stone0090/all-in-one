package com.stone0090.aio.service.model.service.dag;

import lombok.Data;

@Data
public class DagData {
    private DagNode[] nodes;
    private DagEdge[] edges;
}
