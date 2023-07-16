package com.stone0090.aio.service.model.web.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class RoleVO implements Serializable {
    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private String roleCode;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private List<PermissionVO> permissions;
}
