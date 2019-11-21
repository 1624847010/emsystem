package com.em.controller;

import com.em.service.CommentService;
import com.em.util.BaseResponse;
import com.em.vo.Address;
import com.em.vo.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 9:36
 **/
@RestController
@RequestMapping("/comment")
@Api(description = "评论管理")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //1是好评，0是差评
    @ApiOperation(value = "根据商家id查询评论列表（好评 差评 全部）")
    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse<Comment>> getCommentList(@RequestParam(value = "shopId") Integer shopId, @RequestParam(value = "type") Integer id){
        try {
            //获取用户列表
            List<Comment> commentList = commentService.getCommentList(shopId,id);
            return BaseResponse.generateOKListResponseEntity(commentList);
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "用户插入评论")
    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse<Comment>> addComment(@RequestBody Comment comment){
        try {
            int count = commentService.addComment(comment);
            if (count > 0) {
                return BaseResponse.generateOKResponseEntity("新增成功");
            }
            return BaseResponse.generateBadResponseEntity(500,"新增失败","");
        }catch (Exception e){
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
}
