package com.xy.configration.annotation;

import java.lang.annotation.*;

/**
 * @description: 从库注解
 * @author: xuyuan
 * @create: 2018-11-20 22:33
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Slave {

}
