package com.em.service;

import com.em.vo.User;

import java.util.List;

public interface UserService {
    List<User> getUserList(int pageSize, int pageNum, String name);

    User loginSelect(User user);

    int addUser(User user);

    int updateUser(User user);

    int delUser(User user);

    User phoneSelect(User user);
}
