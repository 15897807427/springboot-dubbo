package com.xy.service;

import com.xy.annotation.Master;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-11-21 18:23
 **/
@Service
public class TestService {

    @Master
    public String hello(){
        return "xy";
    }

}
