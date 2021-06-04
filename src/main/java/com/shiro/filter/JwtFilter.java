package com.shiro.filter;

import com.shiro.token.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 大静
 * @version 1.0
 * @date 2021-04-29 9:47
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
  /**
   * 判断是否登录
   *
   * @param servletRequest
   * @param servletResponse
   * @return
   */
  @Override
  protected boolean isLoginAttempt(ServletRequest servletRequest, ServletResponse servletResponse) {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String authToken = request.getHeader("token");
    return authToken != null;
  }

  @Override
  protected boolean isAccessAllowed(
      ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
    if (isLoginAttempt(servletRequest, servletResponse)) {
      try {
        executeLogin(servletRequest, servletResponse);
        return true;
      } catch (Exception e) {
        servletRequest.setAttribute("msg", e.getMessage());
        responseUnAuthor(servletRequest, servletResponse);
      }
    }
    return true;
  }

  @Override
  public boolean executeLogin(ServletRequest servletRequest, ServletResponse servletResponse) {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String authorization = request.getHeader("Authorization");
    JwtToken token = new JwtToken(authorization);
    try {
      getSubject(servletRequest, servletResponse).login(token);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse)
      throws Exception {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
    response.setHeader(
        "Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
    if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
      response.setStatus(HttpStatus.OK.value());
      return false;
    }
    return super.preHandle(servletRequest, servletResponse);
  }

  /**
   * 非法请求提交未授权页面
   *
   * @param servletRequest
   * @param servletResponse
   */
  public void responseUnAuthor(ServletRequest servletRequest, ServletResponse servletResponse) {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    try {
      request.getRequestDispatcher("/auth/unAuth").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
