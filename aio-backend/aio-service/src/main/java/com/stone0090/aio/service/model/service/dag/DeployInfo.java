package com.stone0090.aio.service.model.service.dag;

import lombok.Data;

@Data
public class DeployInfo extends DagData {
    String requestId;
    ScheduleInfo scheduleInfo;
}
