package com.xy.controller;

import com.xy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-11-21 18:15
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("hello")
    public String hello(){
        return  testService.hello();
    }

}
