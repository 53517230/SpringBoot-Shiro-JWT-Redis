package com.shiro.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author 大静
 * @version 1.0
 * @date 2021-04-29 9:08
 */
public class JwtUtils {
  /** token过期时长 */
  private static final long EXPIRE_TIME = 1000 * 60 * 5;
  /** 私钥 */
  private static final String SECRET = "205379e2abd3ac305b8cc5c414ce6199";

  /**
   * 生成token令牌，5分钟后过期
   *
   * @param username
   * @return
   */
  public static String createToken(String username) {
    try {
      Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
      Algorithm algorithm = Algorithm.HMAC256(SECRET);
      return JWT.create()
          .withClaim("username", username)
          .withExpiresAt(date)
          .sign(algorithm);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 校验token是否正确
   *
   * @param token
   * @param username
   * @return
   */
  public static boolean verify(String token, String username) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET);
      JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
      verifier.verify(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 解析token中username
   *
   * @param token
   * @return
   */
  public static String getToken(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("username").asString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
