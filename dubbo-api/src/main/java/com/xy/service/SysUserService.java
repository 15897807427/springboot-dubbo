package com.xy.service;

import com.xy.entity.SysUser;

/**
 * @description: 用户service接口类
 * @author: xuyuan
 * @create: 2018-11-18 02:03
 **/
public interface SysUserService {

    public SysUser findUserById(Integer id);

    public void insertSysUser(SysUser sysUser);

}
