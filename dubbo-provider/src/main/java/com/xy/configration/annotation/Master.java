package com.xy.configration.annotation;

import java.lang.annotation.*;

/**
 * @description: 主库注解
 * @author: xuyuan
 * @create: 2018-11-20 22:32
 **/
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Master {
    
}
