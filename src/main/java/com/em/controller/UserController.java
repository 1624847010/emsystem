package com.em.controller;

import com.em.mapper.FileMapper;
import com.em.service.UserService;
import com.em.util.BaseResponse;
import com.em.util.IDUtil;
import com.em.vo.File;
import com.em.vo.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ll
 * @Date 2019/11/12
 * @Time 8:40
 **/
@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileMapper fileMapper;
    private final Log log = LogFactory.getLog(getClass());

    @ApiOperation(value = "分页查询用户")
    @GetMapping(value = "/getUserList")
    public ResponseEntity<BaseResponse<User>> getUserList(@RequestParam("pageSize") int pageSize,@RequestParam("pageNum") int pageNum,@RequestParam("name")String name){
        try {
            //获取用户列表
            List<User> userList = userService.getUserList(pageSize,pageNum,name);
            return BaseResponse.generateOKListResponseEntity(userList);
        }catch (Exception e){
            log.debug("UserController-->>getUserList",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public ResponseEntity<BaseResponse<User>> login(@RequestBody User user){
        User theUser = userService.loginSelect(user);
        if (theUser == null) {
            return BaseResponse.generateBadResponseEntity(500,"账户不存在","");
        }
        if (!theUser.getPwd().equals(user.getPwd())) {
            return BaseResponse.generateBadResponseEntity(500,"密码错误","");
        }
        return BaseResponse.generateOKResponseEntity("登陆成功",theUser);
    }
    @ApiOperation(value = "id查询信息")
    @PostMapping(value = "/selectById")
    public ResponseEntity<BaseResponse<User>> selectById(@RequestBody User user){
        User theUser = userService.loginSelect(user);
        return BaseResponse.generateOKResponseEntity("查询成功",theUser);
    }
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/addUser")
    public ResponseEntity<BaseResponse<User>> addUser(@RequestBody User user){
        User theUser = userService.loginSelect(user);
        if (theUser != null) {
            return BaseResponse.generateBadResponseEntity(500,"新增失败,手机号已被注册","");
        }
        user.setUserName(IDUtil.getID().substring(20));
        user.setMoney(0f);
        int count = userService.addUser(user);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("新增成功","");
        }
        else {
            return BaseResponse.generateBadResponseEntity(500,"新增失败","");
        }
    }
    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/updateUser")
    public ResponseEntity<BaseResponse<User>> updateUser(@RequestBody User user){
        try {
            int count = userService.updateUser(user);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("修改成功","");
            }else {
                return BaseResponse.generateBadResponseEntity(500,"修改失败","");
            }
        }catch (Exception e){
            log.debug("UserController-->>updateUser",e);
            return BaseResponse.generateBadResponseEntity(500,"请输入参数","");
        }
    }
    @ApiOperation(value = "注销用户")
    @DeleteMapping(value = "/delUser")
    public ResponseEntity<BaseResponse<User>> delUser(@RequestBody User user){
        int count = userService.delUser(user);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("注销成功","");
        }else {
            return BaseResponse.generateBadResponseEntity(500,"注销失败","");
        }
    }
}
