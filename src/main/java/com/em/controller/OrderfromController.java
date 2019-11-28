package com.em.controller;

import com.em.service.OrderfromService;
import com.em.util.BaseResponse;
import com.em.vo.Orderfrom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
                                                                 @RequestParam Date time,
                                                                 @RequestParam Integer shopId){
        try {
            //获取用户列表
            List<Orderfrom> orderfromList = service.getListByShop(page,limit,isSend,isComment,time,shopId);
            return BaseResponse.generateOKListResponseEntity(orderfromList);
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "用户查询订单")
    @GetMapping(value = "/listByUser")
    public ResponseEntity<BaseResponse<Orderfrom>> listByUser(@RequestParam(value = "userId") Integer userId){
//        try {
            //获取用户列表
            List<Orderfrom> orderfromList = service.listByUser(userId);
            return BaseResponse.generateOKListResponseEntity(orderfromList);
//        }catch (Exception e){
//            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
//        }
    }
    @ApiOperation(value = "插入订单")
    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse<Orderfrom>> addOrder(@RequestBody Orderfrom orderfrom){
//        try {
            JSONObject jsonObj = new JSONObject(orderfrom.getGoodsJson());
            orderfrom.setGoods(jsonObj.toString());
            int count = service.addOrder(orderfrom);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("新增成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"新增失败","");
//        }catch (Exception e){
//            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
//        }
    }
    @ApiOperation(value = "修改订单")
    @PostMapping(value = "/update")
    public ResponseEntity<BaseResponse<Orderfrom>> updateOrder(@RequestBody Orderfrom orderfrom){
//        try {
//            JSONObject jsonObj = new JSONObject(orderfrom.getGoodsJson());
//            orderfrom.setGoods(jsonObj.toString());
            int count = service.updateOrder(orderfrom);
//            System.out.println(jsonObj.toString());
//            System.out.println(jsonObj);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("修改成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
//        }catch (Exception e){
//            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
//        }
    }
}
