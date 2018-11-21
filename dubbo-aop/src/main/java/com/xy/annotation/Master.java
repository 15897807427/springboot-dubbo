package com.xy.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-11-21 18:16
 **/
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Master {

}
