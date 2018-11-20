package com.xy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xy.entity.SysUser;
import com.xy.service.SysUserService;
import com.xy.util.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: SysUser控制器
 * @author: xuyuan
 * @create: 2018-11-18 02:35
 **/
@RequestMapping(value = "/sysUser")
@Controller
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class SysUserController {

    @Reference(version = "1.0",check = false,timeout = 10000,retries = -1)
    private SysUserService sysUserService;

    /**
     * 超时时间设置为5s，超过时会触发降级，10s内请求数达到10以上，并且错误率在50%以上，会触发熔断，并且休眠5s,否则不会考虑熔断
     * 采用THREAD线程池隔离,初始线程数是8，当队列达到16以后直接拒绝请求，触发降级
     * 请求超时，请求拒绝都会记录，影响熔断
     */
    @RequestMapping(value = "/findUserById")
    @ResponseBody
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")},
            threadPoolProperties = {@HystrixProperty(name="coreSize",value="8"),
                    @HystrixProperty(name="maxQueueSize",value="20")})
    public Object findUserById(Integer id){
        try {
            SysUser sysUser = sysUserService.findUserById(id);
            return ResponseVO.success(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e.getCause());
            return ResponseVO.exception(e.getMessage());
        }
    }

    /**
     * 本方法包含插入方法，超时抛异常，或者降级，都会导致数据不一致问题，故关闭超时开关
     * 采用THREAD线程池隔离,初始线程数是5，当队列达到8以后直接拒绝请求，触发降级
     * 请求拒绝都会记录，影响熔断
     */
    @RequestMapping(value = "/insertUser")
    @ResponseBody
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")},
            threadPoolProperties = {@HystrixProperty(name="coreSize",value="5"),
                    @HystrixProperty(name="maxQueueSize",value="10")})
    public Object insertUser(String name){
        try {
            SysUser user=new SysUser();
            user.setName(name);
            sysUserService.insertSysUser(user);
            return ResponseVO.success("插入用户成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e.getCause());
            return ResponseVO.exception(e.getMessage());
        }
    }

    public Object defaultFallback(){
        return ResponseVO.exception("请求处理异常");
    }

}
