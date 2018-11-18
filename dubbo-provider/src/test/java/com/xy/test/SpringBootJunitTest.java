package com.xy.test;

import com.xy.entity.SysUser;
import com.xy.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: springboot测试类
 * @author: xuyuan
 * @create: 2018-11-18 10:52
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootJunitTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testMybatis(){
        SysUser userById = sysUserService.findUserById(1);
        System.out.println(userById);
    }

}
