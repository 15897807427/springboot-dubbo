package com.xy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-11-21 18:19
 **/
@Slf4j
@Aspect
@Component
public class MasterAspect {

    @Pointcut(value = "@annotation(com.xy.annotation.Master)")
    public void masterPointcut(){

    }

    @Before("masterPointcut()")
    public void beforeMasterPointcut(){
        log.error("执行前进入了！");
    }

}
