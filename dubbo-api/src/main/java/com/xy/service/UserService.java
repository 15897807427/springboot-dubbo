package com.xy.service;

import com.xy.entity.User;

/**
 * @description: 用户服务类接口
 * @author: xuyuan
 * @create: 2018-11-16 23:44
 **/
public interface UserService {

    /**
     * @Description: 通过用户名获取用户对象
     * @Author: xuyuan
     * @Date: 2018/11/16 0016 23:47
     */
    public User getUserByName(String userName);

}
