package com.shiro.realm;

import com.shiro.dao.PermsMapper;
import com.shiro.dao.RoleMapper;
import com.shiro.dao.UserMapper;
import com.shiro.entity.Perms;
import com.shiro.entity.Role;
import com.shiro.entity.User;
import com.shiro.token.JwtToken;
import com.shiro.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月28日 15:54
 */
public class ShiroRealm extends AuthorizingRealm {
  @Autowired private UserMapper userMapper;
  @Autowired private RoleMapper roleMapper;
  @Autowired private PermsMapper permsMapper;

  /**
   * 授权
   *
   * @param principalCollection
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    String username = JwtUtils.getToken(principalCollection.toString());
    User user = userMapper.findUser(username);
    List<String> rolesList = new ArrayList<>();
    List<String> permsList = new ArrayList<>();
    List<Role> roles = roleMapper.queryRole(user.getId());
    for (Role role : roles) {
      rolesList.add(role.getRolename());
      List<Perms> perms = permsMapper.queryPerms(role.getId());
      for (Perms perm : perms) {
        permsList.add(perm.getPerms());
      }
    }
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addStringPermissions(permsList);
    info.addRoles(rolesList);
    return info;
  }

  /**
   * 必须重写此方法，不然报错
   *
   * @param token
   * @return
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtToken;
  }

  /**
   * 认证、登录
   *
   * @param authenticationToken
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    if (authenticationToken.getPrincipal() == null) {
      return null;
    }
    String token = (String) authenticationToken.getCredentials();
    String username = JwtUtils.getToken(token);
    System.out.println(username);
    if (username == null) {
      throw new AuthenticationException("token已过期，请重新登录");
    }
    User user = userMapper.findUser(username);
    if (user == null) {
      throw new UnknownAccountException("当前用户不存在");
    }
    if (!JwtUtils.verify(token, username)) {
      throw new IncorrectCredentialsException("校验token失败");
    }
    return new SimpleAuthenticationInfo(token, token, this.getName());
  }
}
