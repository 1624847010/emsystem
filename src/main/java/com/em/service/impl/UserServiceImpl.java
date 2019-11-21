package com.em.service.impl;

import com.em.mapper.UserMapper;
import com.em.service.UserService;
import com.em.vo.User;
import com.em.vo.UserExample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/12
 * @Time 10:28
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    private final Log log = LogFactory.getLog(getClass());

    @Override
    public List<User> getUserList(int pageSize, int pageNum, String name) {
        UserExample userExample = new UserExample();
        //通过模糊姓名查询查询
        userExample.createCriteria().andUserNameLike('%'+name+'%');
        int pageNo = (pageNum-1)*pageSize;
        userExample.setPageNum(pageNo);
        userExample.setSize(pageSize);
        return mapper.selectByExample(userExample);
    }

    @Override
    public User loginSelect(User user) {
        //根据用户手机号返回用户信息
        return mapper.loginSelect(user);
    }

    @Override
    public int addUser(User user) {
        //新增用户
        return mapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        //修改用户
        return mapper.update(user);
    }

    @Override
    public int delUser(User user) {
        //注销用户
        return mapper.delUser(user);
    }
}
