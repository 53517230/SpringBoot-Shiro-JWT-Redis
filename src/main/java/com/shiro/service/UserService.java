package com.shiro.service;

import com.shiro.entity.User;

/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月29日 10:21
 */
public interface UserService {

    User findUser(String username);

    User loadUser(Long id);

    boolean updateStatus(Integer statusCode,Long id);
}
