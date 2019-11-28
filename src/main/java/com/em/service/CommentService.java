package com.em.service;

import com.em.vo.Address;
import com.em.vo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentList(Integer shopId, Integer id,Integer pageSize,Integer pageNum);

    int addComment(Comment comment);

    Comment selectById(Integer id);

    float selectGradeAvg(Long id);
}
