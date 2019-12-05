package com.em.controller;

import com.em.service.GoodsService;
import com.em.util.BaseResponse;
import com.em.vo.Goods;
import com.em.vo.Merchant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 17:36
 **/
@RestController
@Api(description = "商品管理")
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "新增商品")
    @PostMapping("/addGoods")
    public ResponseEntity<BaseResponse<Goods>> addGoods(@RequestBody Goods goods){
        int count = goodsService.addGoods(goods);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("新增成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("新增失败","");
        }
    }
    @ApiOperation(value = "下架商品/重新上架")
    @PostMapping("/delGoods")
    public ResponseEntity<BaseResponse<Goods>> delGoods(@RequestBody Goods goods){
        int count = goodsService.updateGoods(goods);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("下架成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("下架失败","");
        }
    }

    @ApiOperation(value = "编辑商品")
    @PutMapping("/updateGoods")
    public ResponseEntity<BaseResponse<Goods>> updateGoods(@RequestBody Goods goods){
        int count = goodsService.updateGoods(goods);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("编辑成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("编辑失败","");
        }
    }
    //后台查询传入type,不为0则查询上下架商品，为0则只显示上架商品
    @ApiOperation(value = "查询商品列表")
    @GetMapping("/selectGoods")
    public ResponseEntity<BaseResponse<Goods>> selectGoods(@RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                                           @RequestParam(defaultValue = "")String goodsName,
                                                           @RequestParam(defaultValue = "0")Integer type,
                                                           @RequestParam() Integer shopId){
        List<Goods> list = goodsService.selectGoods(pageSize,pageNum,goodsName,shopId,type);
        return BaseResponse.generateOKListResponseEntity(list);
    }
    @ApiOperation(value = "根据商品id查询商品信息")
    @GetMapping("/selectGoodsById")
    public ResponseEntity<BaseResponse<Goods>> selectGoodsById(@RequestParam() Integer id){
        List<Goods> list = goodsService.selectGoodsInfo(id);
        return BaseResponse.generateOKListResponseEntity(list);
    }
}
