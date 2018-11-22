package com.xy;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description: springboot启动类
 * @author: xuyuan
 * @create: 2018-11-18 01:02
 **/
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@EnableCircuitBreaker
@EnableDubbo
public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class,args);
    }
}
