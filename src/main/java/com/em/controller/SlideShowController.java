package com.em.controller;

import com.em.service.SlideShowService;
import com.em.util.BaseResponse;
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
 * @Time 13:41
 **/
@Api(description = "轮播图管理")
@RestController
@RequestMapping("/slide")
public class SlideShowController {
    @Autowired
    private SlideShowService service;

    //status是否禁用（0为未禁用，1为禁用,-1为全部）
    @ApiOperation(value = "查询轮图列表")
    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse<Slideshow>> getSlideshowList(@RequestParam(defaultValue = "1") Integer page,
                                                                    @RequestParam(defaultValue = "10") Integer limit,
                                                                    @RequestParam(defaultValue = "-1") Integer status){
        try {
            //获取用户列表
            List<Slideshow> slideshowsList = service.getSlideshowList(page,limit,status);
            return BaseResponse.generateOKListResponseEntity(slideshowsList);
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }

    @ApiOperation(value = "新增轮播图")
    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse<Slideshow>> addSlideshows(@RequestBody Slideshow slideshow){
        try {
            //获取用户列表
            int count = service.addSlideshows(slideshow);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("新增成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"新增失败,请插入图片","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "删除轮播图")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse<Slideshow>> deleteSlideshows(@RequestParam(value = "id")Integer id){
        try {
            //获取用户列表
            int count = service.deleteSlideshows(id);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("删除成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"删除失败,请插入图片","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "禁用/开启轮播图")
    @PutMapping(value = "/update")
    public ResponseEntity<BaseResponse<Slideshow>> updateSlideshows(@RequestBody Slideshow slideshow){
        try {
            //获取用户列表
            int count = service.updateSlideshows(slideshow);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("修改成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
}
