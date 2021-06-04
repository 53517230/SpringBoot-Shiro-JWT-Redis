package com.shiro.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 大静是我女儿
 * @version 1.0
 * @date 2021年04月29日 10:29
 */
@Data
public class JsonResult implements Serializable {

  private boolean success;

  private String message;

  private Object data;

  public static JsonResult success(String msg) {
    JsonResult json = new JsonResult();
    json.setSuccess(true);
    json.setMessage(msg);
    return json;
  }

  public static JsonResult success(String msg, Object data) {
    JsonResult json = new JsonResult();
    json.setSuccess(true);
    json.setMessage(msg);
    json.setData(data);
    return json;
  }

  public static JsonResult failed(String msg) {
    JsonResult json = new JsonResult();
    json.setSuccess(false);
    json.setMessage(msg);
    return json;
  }
}
