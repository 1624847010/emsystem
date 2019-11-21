package com.em.controller;

import com.em.service.AddressService;
import com.em.util.BaseResponse;
import com.em.vo.Address;
import com.em.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 9:01
 **/
@Api(description = "地址管理")
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "根据用户id查询地址列表")
    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse<Address>> getAddressList(@RequestParam(value = "id") Integer id){
        try {
            //获取用户列表
            List<Address> userList = addressService.getAddressList(id);
            return BaseResponse.generateOKListResponseEntity(userList);
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "新增用户地址")
    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse<Address>> addAddress(@RequestBody Address address){
        try {
            int count = addressService.addAddress(address);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("新增成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"新增失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "修改用户地址")
    @PutMapping(value = "/update")
    public ResponseEntity<BaseResponse<Address>> updateAddress(@RequestBody Address address){
        try {
            int count = addressService.updateAddress(address);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("修改成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "删除用户地址")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse<Address>> deleteAddress(@RequestParam(value = "id") Integer id){
        try {
            int count = addressService.deleteAddress(id);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("删除成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"删除失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
}
