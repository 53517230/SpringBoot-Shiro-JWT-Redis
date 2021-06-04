package com.shiro.service.Impl;

import com.shiro.dao.UserMapper;
import com.shiro.entity.User;
import com.shiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月29日 10:22
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired private UserMapper userMapper;

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  @Override
  public User findUser(String username) {
    Assert.notNull(username, "参数错误");
    User user = userMapper.findUser(username);
    if (user == null) {
      log.info("当前用户不存在");
    }
    return user;
  }

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  @Override
  public User loadUser(Long id) {
    Assert.notNull(id, "参数错误");
    User user = userMapper.selectByPrimaryKey(id);
    if (user == null) {
      log.info("当前用户不存在");
    }
    return user;
  }

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  @Override
  public boolean updateStatus(Integer statusCode, Long id) {
    Assert.notNull(statusCode, "参数错误");
    if (statusCode > 2 || statusCode < -1) {
      log.error("状态码错误，1 正常、0 禁用");
    }
    User user = loadUser(id);
    int res = userMapper.updateStatus(statusCode, user.getId());
    if (res > 0) {
      return true;
    }
    return false;
  }
}
