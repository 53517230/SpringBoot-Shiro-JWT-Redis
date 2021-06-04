package com.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 大静
 * @version 1.0
 * @date 2021-04-29 9:05
 */
@Data
public class JwtToken implements AuthenticationToken {

  private String token;

  public JwtToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }
}
