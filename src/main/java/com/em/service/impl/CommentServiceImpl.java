package com.em.service.impl;

import com.em.mapper.CommentMapper;
import com.em.service.CommentService;
import com.em.service.FileService;
import com.em.vo.Address;
import com.em.vo.Comment;
import com.em.vo.CommentExample;
import com.em.vo.File;
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
    //评论管理的业务id
    private Integer commentType = 4;


    @Override
    public List<Comment> getCommentList(Integer shopId, Integer id) {
        CommentExample example = new CommentExample();
        switch (id){
            case 1:
                example.createCriteria().andShopIdEqualTo(shopId).andGradeGreaterThanOrEqualTo(3f);
                break;
            case 0:
                example.createCriteria().andShopIdEqualTo(shopId).andGradeLessThan(3F);
                break;
            default:
                example.createCriteria().andShopIdEqualTo(shopId);
                break;
        }
        List<Comment> comments = commentMapper.selectByExample(example);
        for (int i = 0; i <comments.size() ; i++) {
            File file = new File();
            file.setBusinessId(Math.toIntExact(comments.get(i).getId()));
            file.setBusinessType(commentType);
            List<File> list = fileService.selectFile(file);
            if(list != null){
                comments.get(i).setImg(list.get(0));
            }
        }
        return comments;
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
        return i;
    }
}
