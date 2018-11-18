package com.xy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xy.entity.SysUser;
import com.xy.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: SUser控制器
 * @author: xuyuan
 * @create: 2018-11-18 02:35
 **/
@RequestMapping(value = "/sysUser")
@Controller
public class SysUserController {

    @Reference(version = "1.0",check = false,timeout = 5000)
    private SysUserService sysUserService;

    @RequestMapping(value = "/findUserById")
    @ResponseBody
    public SysUser findUserById(Integer id){
        SysUser sysUser = sysUserService.findUserById(id);
        return sysUser;
    }
}
