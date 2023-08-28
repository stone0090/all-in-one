package com.stone0090.aio.service.model.web.request.save;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author stone
 * @date 2021/07/18
 */
@Data
public class DagSaveDetailRequest implements Serializable {

    @NotNull(message = "DagID不能为空")
    private Integer id;

    @NotNull(message = "Dag节点不能为空")
    @Size(max = 20000, message = "Dag节点必须小于20000位字符")
    private String dagData;

//    @NotNull(message = "部署类型不能为空")
//    @Size(max = 50, message = "部署类型必须小于50位字符")
//    private String publishType;

}
