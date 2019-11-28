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
    @ApiOperation(value = "删除商品")
    @DeleteMapping("/delGoods")
    public ResponseEntity<BaseResponse<Goods>> delGoods(@RequestBody Goods goods){
        int count = goodsService.delGoods(goods);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("删除成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("删除失败","");
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
    @ApiOperation(value = "查询商品列表")
    @GetMapping("/selectGoods")
    public ResponseEntity<BaseResponse<Goods>> selectGoods(@RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                                           @RequestParam(defaultValue = "")String goodsName,
                                                           @RequestParam() Integer shopId){
        List<Goods> list = goodsService.selectGoods(pageSize,pageNum,goodsName,shopId);
        return BaseResponse.generateOKListResponseEntity(list);
    }
    @ApiOperation(value = "根据商品id查询商品信息")
    @GetMapping("/selectGoodsById")
    public ResponseEntity<BaseResponse<Goods>> selectGoodsById(@RequestParam() Integer id){
        List<Goods> list = goodsService.selectGoodsInfo(id);
        return BaseResponse.generateOKListResponseEntity(list);
    }
}
