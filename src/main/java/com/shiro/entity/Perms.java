package com.shiro.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_permission
 * @author 
 */
@Data
public class Perms implements Serializable {
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 父级Id
     */
    private Long parentid;

    /**
     * 权限串
     */
    private String perms;

    /**
     * 是否为叶子节点 1 是,0 否
     */
    private Boolean level;

    /**
     * 1 正常, 0 禁用
     */
    private Integer status;

    /**
     * 页面跳转路径
     */
    private String url;

    /**
     * 类型
     */
    private String type;

    private static final long serialVersionUID = 1L;
}