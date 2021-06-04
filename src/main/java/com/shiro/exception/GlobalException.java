package com.shiro.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月30日 16:37
 */
@RestControllerAdvice
public class GlobalException {
  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public JSONObject jsonObject(HttpServletResponse response) {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("errorCode", 401);
    jsonObject.put("errorMsg", "未拥有权限");
    return jsonObject;
  }
}
