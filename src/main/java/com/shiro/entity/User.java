package com.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * t_user
 * @author 
 */
@Data
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String salt;

    /**
     * 1 正常,0 禁用
     */
    private Integer status;

    private String img;

    private static final long serialVersionUID = 1L;
}