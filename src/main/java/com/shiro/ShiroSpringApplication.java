package com.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月28日 18:02
 */
@SpringBootApplication
@MapperScan(basePackages = "com.shiro.dao")
@EnableTransactionManagement
public class ShiroSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShiroSpringApplication.class, args);
  }
}
