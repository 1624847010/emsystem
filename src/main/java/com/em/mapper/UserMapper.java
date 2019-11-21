package com.em.mapper;

import com.em.vo.User;
import com.em.vo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Nov 12 16:34:42 CST 2019
     */
    List<User> selectByExample(UserExample example);


    //根据用户账号查询用户
    User loginSelect(User user);

    //修改用户信息
    int update(User user);

    //删除用户
    int delUser(User user);
}