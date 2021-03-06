package com.em.service.impl;

import com.em.mapper.UserMapper;
import com.em.service.FileService;
import com.em.service.UserService;
import com.em.vo.File;
import com.em.vo.User;
import com.em.vo.UserExample;
import com.github.pagehelper.PageHelper;
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
    @Autowired
    private FileService fileService;
    //用户头像id
    private static final Integer USERTYPE = 6;

    @Override
    public List<User> getUserList(int pageSize, int pageNum, String name) {
        UserExample userExample = new UserExample();
        //通过模糊姓名查询查询
        userExample.createCriteria().andUserNameLike('%'+name+'%');
        //分页查询
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectByExample(userExample);
    }

    @Override
    public User loginSelect(User user) {
        //根据用户id返回用户信息
        User loginUser = mapper.loginSelect(user);
        File file = new File();
        file.setBusinessType(USERTYPE);
        file.setBusinessId(Math.toIntExact(loginUser.getId()));
        List<File> list = fileService.selectFile(file);
        if (list.size()!=0) {
            loginUser.setFile(list.get(0));
        }
        return loginUser;
    }

    @Override
    public int addUser(User user) {
        //新增用户
        return mapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        //修改用户
        int update = 0;
        //是否需要修改信息
        if (user.getUserName()!=null||user.getPwd()!=null||user.getMoney()!=null){
            update = mapper.update(user);
        }
        //是否需要修改图片
        if (user.getFile() != null) {
            File myfile = new File();
            myfile.setBusinessId(Math.toIntExact(user.getId()));
            myfile.setBusinessType(USERTYPE);
            List<File> list = fileService.selectFile(myfile);
            if (list.size()==0) {
                return insertFile(user);
            }else {
                boolean b = fileService.delFile(myfile);
                if (b) {
                    return insertFile(user);
                }
            }
        }
        return update;
    }
    //插入图片
    private int insertFile(User user) {
        File file = new File();
        file.setBusinessType(USERTYPE);
        file.setBusinessId(Math.toIntExact(user.getId()));
        file.setFileId(user.getFile().getFileId());
        file.setFileUrl(user.getFile().getFileUrl());
        int i = fileService.saveFile(file);
        return i;
    }

    @Override
    public int delUser(User user) {
        //注销用户
        return mapper.delUser(user);
    }

    @Override
    public User phoneSelect(User user) {
        //根据用户手机号返回用户信息
        User loginUser = mapper.phoneSelect(user);
        return loginUser;
    }
}
