package com.shiro.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月28日 16:55
 */
@Component
public class UUIDUtils {
  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
