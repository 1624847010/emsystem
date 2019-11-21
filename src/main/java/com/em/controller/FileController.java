package com.em.controller;

import com.em.service.FileService;
import com.em.util.BaseResponse;
import com.em.vo.File;
import com.em.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/13
 * @Time 14:17
 **/
@RestController
@Api(description = "图片管理")
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadFile")
    public ResponseEntity<BaseResponse<User>> uploadFile(MultipartFile file){
        File file1 = fileService.uploadFile(file);
        if (file1!=null) {
            return BaseResponse.generateOKResponseEntity("上传成功", file1);
        }else {
            return BaseResponse.generateBadResponseEntity(500,"上传失败","");
        }
    }
    @ApiOperation(value = "删除文件")
    @PostMapping("/delFile")
    public ResponseEntity<BaseResponse<User>> delFile(@RequestBody File file){
        boolean b = fileService.delFile(file);
        if (b) {
            return BaseResponse.generateOKResponseEntity("删除成功", "");
        }else {
            return BaseResponse.generateBadResponseEntity(500,"删除失败","");
        }
    }

    @ApiOperation(value = "根据业务查询图片")
    @PostMapping("/selectFile")
    public ResponseEntity<BaseResponse<User>> selectFile(@RequestBody File file){
        List<File> list = fileService.selectFile(file);
        if (list.size() >= 0) {
            return BaseResponse.generateOKListResponseEntity(list);
        }else {
            file.setBusinessType(0);
            file.setBusinessId(0);
            return BaseResponse.generateBadResponseEntity(500,"查询失败失败，返回默认图片",fileService.selectFile(file));
        }
    }
    @ApiOperation(value = "保存图片")
    @PostMapping("/saveFile")
    public ResponseEntity<BaseResponse<User>> saveFile(@RequestBody File file){
        int i = fileService.saveFile(file);
        if (i > 0) {
            return BaseResponse.generateOKResponseEntity("保存成功","");
        }else {
            return BaseResponse.generateBadResponseEntity("保存失败","");
        }
    }
}
