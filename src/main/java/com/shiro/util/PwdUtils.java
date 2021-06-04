package com.shiro.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月28日 15:58
 */
@Component
public class PwdUtils {

  public static String encryptPwd(String password, String salt) {
    return new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 5).toHex();
  }
}
