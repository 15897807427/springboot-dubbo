package com.xy.configration.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: 动态数据源切面
 * @author: xuyuan
 * @create: 2018-11-20 22:36
 **/
@Slf4j
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Pointcut(value="@annotation(com.xy.configration.annotation.Master)")
    public void masterDataSource() {

    }

    @Pointcut(value = "@annotation(com.xy.configration.annotation.Slave)")
    public void slaveDataSource() {

    }

    @Before("masterDataSource()")
    public void masterBeforePointcut(){
        DynamicDataSource.setDataSourceType(DataSourceType.Master);
        log.debug("数据源切换到主库");
    }

    @AfterReturning("masterDataSource()")
    public void masterAfterReturningPointcut(){
        DynamicDataSource.clearDataSourceType();
    }

    @Before("slaveDataSource()")
    public void slaveBeforePointcut(){
        DynamicDataSource.setDataSourceType(DataSourceType.Slave);
        log.debug("数据源切换到从库");
    }

    @AfterReturning("slaveDataSource()")
    public void slaveAfterReturningPointcut(){
        DynamicDataSource.clearDataSourceType();
    }

}
