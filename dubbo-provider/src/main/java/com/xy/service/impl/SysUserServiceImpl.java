package com.xy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xy.configration.annotation.Master;
import com.xy.configration.annotation.Slave;
import com.xy.entity.SysUser;
import com.xy.persistence.SysUserMapper;
import com.xy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 用户服务类接口
 * @author: xuyuan
 * @create: 2018-11-16 23:44
 **/
@Component
@Service(version = "1.0",interfaceClass = SysUserService.class)
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Slave
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public SysUser findUserById(Integer id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        return sysUser;
    }

    @Override
    @Master
    public void insertSysUser(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }
}
