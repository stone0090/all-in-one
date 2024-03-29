package com.stone0090.aio.service.model.web.response;

import java.io.Serializable;

import lombok.Data;

/**
 * @author stone
 * @date 2021/07/26
 */
@Data
public class UserVO implements Serializable {
    private String name;
    private String avatar;
    private String userid;
    private String email;
    private String signature;
    private String title;
    private String group;
    private Integer notifyCount;
    private Integer unreadCount;
    private String country;
    private String access;
}