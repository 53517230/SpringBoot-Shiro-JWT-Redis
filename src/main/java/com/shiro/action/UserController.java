package com.shiro.action;

import com.shiro.domain.JsonResult;
import com.shiro.entity.User;
import com.shiro.service.UserService;
import com.shiro.util.JwtUtils;
import com.shiro.util.PwdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月29日 10:26
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @ApiOperation(value = "登录")
  @PostMapping("/login")
  public JsonResult userLogin(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      HttpServletRequest request) {
    User user = userService.findUser(username);
    if (user.getPassword().equals(PwdUtils.encryptPwd(password, user.getSalt()))) {
      String token = JwtUtils.createToken(username);
      HttpSession session = request.getSession();
      session.setAttribute("token", token);
      return JsonResult.success("登陆成功", token);
    }
    return JsonResult.failed("登陆失败");
  }

  @ApiOperation(value = "更改状态")
  @PutMapping("/updateStatus")
  @RequiresRoles(
      logical = Logical.AND,
      value = {"Admin", "User"})
  public JsonResult updateStatus(int statusCode, Long id) {
    boolean res = userService.updateStatus(statusCode, id);
    if (res) {
      return JsonResult.success("更改用户状态成功");
    }
    return JsonResult.failed("更改用户状态失败");
  }

  @ApiOperation(value = "登出")
  @GetMapping("/logout")
  public void userLogout(HttpServletRequest request) {
    try {
      request.getSession().removeAttribute("token");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
