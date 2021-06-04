package com.shiro.dao;

import com.shiro.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
  int deleteByPrimaryKey(Long id);

  int insertSelective(User record);

  User selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(User record);

  User findUser(String username);

    int updateStatus(Integer statusCode, Long id);
}
