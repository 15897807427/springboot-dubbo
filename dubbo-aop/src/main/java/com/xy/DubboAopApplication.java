package com.xy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-11-21 22:32
 **/
@SpringBootApplication
@EnableAspectJAutoProxy
public class DubboAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboAopApplication.class,args);
    }
}
