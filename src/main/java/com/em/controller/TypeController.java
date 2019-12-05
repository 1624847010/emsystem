package com.em.controller;

import com.em.service.TypeService;
import com.em.util.BaseResponse;
import com.em.vo.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/18
 * @Time 15:14
 **/
@Api(description = "店铺分类管理/商家分类管理")
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "新增分类")
    @PostMapping("/addType")
    public ResponseEntity<BaseResponse<Type>> addType(@RequestBody Type type){
        int count = typeService.addType(type);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("新增成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("新增失败","");
        }
    }
    @ApiOperation(value = "删除分类")
    @DeleteMapping("/delType")
    public ResponseEntity<BaseResponse<Type>> delType(@RequestBody Type type){
        int count = typeService.delType(type);
        System.out.println(count);
        switch (count){
            case 1:
                return BaseResponse.generateOKResponseEntity("删除成功","");
            case 2:
                return BaseResponse.generateBadResponseEntity("删除失败,该分类下有商家", "");
            case 3:
                return BaseResponse.generateBadResponseEntity("删除失败,该分类下有商品", "");
            default:
                return BaseResponse.generateBadResponseEntity("删除失败", "");
        }
    }
    @ApiOperation(value = "修改分类")
    @PutMapping("/updateType")
    public ResponseEntity<BaseResponse<Type>> updateType(@RequestBody Type type){
        int count = typeService.updateType(type);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("修改成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("修改失败","");
        }
    }
    //为0查询商铺分类，不为0则根据商家id查询商品分类
    @ApiOperation(value = "查询分类")
    @GetMapping("/selectType")
    public ResponseEntity<BaseResponse<Type>> selectType(@RequestParam(defaultValue = "0")int shopId){
        if (shopId == 0) {
            //商铺分类
            List<Type> list = typeService.selectShopType();
            return BaseResponse.generateOKResponseEntity("查询成功",list);
        }else {
            //商品分类
            List<Type> list = typeService.selectGoodsType(shopId);
            return BaseResponse.generateOKResponseEntity("查询成功",list);
        }
    }
}
