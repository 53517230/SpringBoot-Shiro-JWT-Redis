package com.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月28日 17:21
 */
public class RoleFilter extends AuthorizationFilter {
  @Override
  protected boolean isAccessAllowed(
      ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
    Subject subject = getSubject(servletRequest, servletResponse);
    String[] roleArrays = (String[]) o;

    if (roleArrays == null || roleArrays.length == 0) {
      return true;
    }
    for (int i = 0; i < roleArrays.length; i++) {
      if (subject.hasRole(roleArrays[0])) {
        return true;
      }
    }
    return false;
  }
}
