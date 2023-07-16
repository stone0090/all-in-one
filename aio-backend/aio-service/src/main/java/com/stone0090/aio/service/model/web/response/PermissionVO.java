package com.stone0090.aio.service.model.web.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class PermissionVO implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String permissionCode;
    private String permissionName;
    private String permissionUrl;
}
