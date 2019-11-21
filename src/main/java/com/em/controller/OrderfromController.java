package com.em.controller;

import com.em.service.OrderfromService;
import com.em.util.BaseResponse;
import com.em.vo.Orderfrom;
import com.em.vo.Slideshow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 14:36
 **/
@Api(description = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderfromController {
    @Autowired
    private OrderfromService service;

    @ApiOperation(value = "商家查询订单")
    @GetMapping(value = "/listByShop")
    public ResponseEntity<BaseResponse<Orderfrom>> getListByShop(@RequestParam Integer page,
                                                                 @RequestParam Integer limit,
                                                                 @RequestParam Integer isSend,
                                                                 @RequestParam Integer isComment,
                                                                 @RequestParam Integer isPay,
                                                                 @RequestParam Integer isClose,
                                                                 @RequestParam Integer shopId){
        try {
            //获取用户列表
            List<Orderfrom> orderfromList = service.getListByShop(page,limit,isSend,isComment,isPay,isClose,shopId);
            return BaseResponse.generateOKListResponseEntity(orderfromList);
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "用户查询订单")
    @GetMapping(value = "/listByUser")
    public ResponseEntity<BaseResponse<Orderfrom>> listByUser(@RequestParam(value = "userId") Integer userId){
        try {
            //获取用户列表
            List<Orderfrom> orderfromList = service.listByUser(userId);
            return BaseResponse.generateOKListResponseEntity(orderfromList);
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "插入查询订单")
    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse<Orderfrom>> addOrder(@RequestBody Orderfrom orderfrom){
        try {
            int count = service.addOrder(orderfrom);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("新增成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"新增失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "修改查询订单")
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse<Orderfrom>> updateOrder(@RequestBody Orderfrom orderfrom){
        try {
            int count = service.updateOrder(orderfrom);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("修改成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
}
