package com.em.service.impl;

import com.em.mapper.CommentMapper;
import com.em.service.CommentService;
import com.em.service.FileService;
import com.em.service.OrderfromService;
import com.em.service.UserService;
import com.em.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/21
 * @Time 9:36
 **/
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderfromService orderfromService;
    //评论管理的业务id
    private Integer commentType = 4;


    @Override
    public List<Comment> getCommentList(Integer shopId, Integer id,Integer pageSize,Integer pageNum) {
        CommentExample example = new CommentExample();
        example.setNum((pageNum-1)*pageSize);
        example.setSize(pageSize);
        example.setOrderByClause("id desc");
        switch (id){
            //好评
            case 1:
                example.createCriteria().andShopIdEqualTo(shopId).andGradeGreaterThanOrEqualTo(3f);
                break;
            //差评
            case 0:
                example.createCriteria().andShopIdEqualTo(shopId).andGradeLessThan(3F);
                break;
             //全部
            default:
                example.createCriteria().andShopIdEqualTo(shopId);
                break;
        }
        List<Comment> comments = commentMapper.selectByExample(example);
        setImgAndUser(comments);
        return comments;
    }
    //设置图片和用户
    private void setImgAndUser(List<Comment> comments) {
        for (int i = 0; i <comments.size() ; i++) {
            //插入图片
            File file = new File();
            file.setBusinessId(Math.toIntExact(comments.get(i).getId()));
            file.setBusinessType(commentType);
            List<File> list = fileService.selectFile(file);
            //插入用户
            User user = new User();
            user.setId(Long.valueOf(comments.get(i).getUserId()));
            User theUser = userService.loginSelect(user);
            if(list.size() != 0){
                comments.get(i).setImg(list.get(0));
            }
            comments.get(i).setUser(theUser);
        }
    }

    @Override
    public int addComment(Comment comment) {
        int i = commentMapper.insertSelective(comment);
        //评论是否有图片
        if (comment.getImg() != null) {
            File file = new File();
            file.setBusinessType(commentType);
            file.setBusinessId(Math.toIntExact(comment.getId()));
            file.setFileUrl(comment.getImg().getFileUrl());
            file.setFileId(comment.getImg().getFileId());
            fileService.saveFile(file);
        }
        if (comment.getId()!=null&&comment.getOrderId()!=null) {
            Orderfrom orderfrom = new Orderfrom();
            orderfrom.setId(Long.valueOf(comment.getOrderId()));
            orderfrom.setIsClose(Math.toIntExact(comment.getId()));
            orderfromService.updateOrder(orderfrom);
        }
        return i;
    }
    //根据id查询评论
    @Override
    public Comment selectById(Integer id) {
        CommentExample example = new CommentExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<Comment> comments = commentMapper.selectByExample(example);
        setImgAndUser(comments);
        if (comments.size()!=0) {
            return comments.get(0);
        }
        return null;
    }

    //根据id查询
    @Override
    public float selectGradeAvg(Long id) {
        float sumGrade = commentMapper.selectAvgById(id);
        return sumGrade;
    }
}
