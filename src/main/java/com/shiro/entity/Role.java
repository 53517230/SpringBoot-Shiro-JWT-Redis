package com.shiro.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_role
 * @author 
 */
@Data
public class Role implements Serializable {
    private Long id;

    private String rolename;

    private String description;

    private static final long serialVersionUID = 1L;
}