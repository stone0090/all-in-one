package com.stone0090.aio.service.model.service.dag;

import lombok.Data;
import java.util.List;

@Data
public class DeployInfo {
    String requestId;
    List<DeployNode> nodes;
}
