package com.em.controller;

import com.em.service.MerchantService;
import com.em.util.BaseResponse;
import com.em.vo.Merchant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 10:07
 **/
@Api(description = "商家管理")
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @ApiOperation(value = "新增商家")
    @PostMapping("/addMerchant")
    public ResponseEntity<BaseResponse<Merchant>> addMerchant(@RequestBody Merchant merchant){
        int count = merchantService.addMerchant(merchant);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("新增成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("新增失败","");
        }
    }


    //查询所有商家
    @ApiOperation(value = "查询所有商家")
    @GetMapping("/selectMerchantList")
    public ResponseEntity<BaseResponse<Merchant>> selectMerchantList(@RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(defaultValue = "1") int pageNum,
                                                                     @RequestParam(defaultValue = "")String shopName,
                                                                     @RequestParam(defaultValue = "0")Integer type,//商家id，0则为用户与管理员
                                                                     @RequestParam(defaultValue = "0")Integer shopType){//分类id
            //当前身份为管理员查询所有商家列表
            List<Merchant> list = merchantService.selectMerchantList(pageSize,pageNum,shopName,shopType,type);
//            int count = merchantService.allCount();
            return BaseResponse.generateOKListResponseEntity(list);
    }
    //修改商家信息
    @ApiOperation(value = "修改商家")
    @PutMapping("/updateMerchant")
    public ResponseEntity<BaseResponse<Merchant>> updateMerchant(@RequestBody Merchant merchant){
        int count = merchantService.updateMerchant(merchant);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("修改成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("修改失败","");
        }
    }
    //注销商家
    @ApiOperation(value = "注销商家")
    @DeleteMapping("/delMerchant")
    public ResponseEntity<BaseResponse<Merchant>> delMerchant(@RequestBody Merchant merchant){
        int count = merchantService.delMerchant(merchant);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("修改成功","");
        }else if (count == -1) {
            return BaseResponse.generateBadResponseEntity("修改失败,该商家目录下有分类","");
        }{
                return BaseResponse.generateBadResponseEntity("修改失败","");
        }
    }
}
