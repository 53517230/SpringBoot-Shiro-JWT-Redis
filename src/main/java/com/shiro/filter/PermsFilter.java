package com.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月28日 17:26
 */
public class PermsFilter extends AuthorizationFilter {
  @Override
  protected boolean isAccessAllowed(
      ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
    Subject subject = getSubject(servletRequest, servletResponse);
    String[] permsArrays = (String[]) o;

    boolean flag = true;
    if (permsArrays != null && permsArrays.length > 0) {
      if (permsArrays.length == 1) {
        if (!subject.isPermitted(permsArrays[0])) {
          flag = false;
        }
      } else {
        if (!subject.isPermittedAll(permsArrays)) {
          flag = false;
        }
      }
    }
    return flag;
  }
}
