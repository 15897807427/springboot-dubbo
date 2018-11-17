package com.xy.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 用户实体类
 * @author xuyuan
 * @date 2018-11-16
 */
@Data
public class User implements Serializable{

    private static final long serialVersionUID = -9190920353110639426L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passwd;

}
