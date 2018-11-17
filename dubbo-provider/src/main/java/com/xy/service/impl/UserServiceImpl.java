package com.xy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xy.entity.User;
import com.xy.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @description: 用户服务类接口
 * @author: xuyuan
 * @create: 2018-11-16 23:44
 **/
@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService{

    @Override
    public User getUserByName(String userName){
        User user=new User();
        user.setUserName(userName);
        user.setPasswd("123");
        return user;
    }

}
