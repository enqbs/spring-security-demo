package com.zhihao.work.service.impl;

import com.zhihao.common.exception.ServiceException;
import com.zhihao.system.pojo.SysPermissions;
import com.zhihao.system.pojo.SysUser;
import com.zhihao.system.service.SysPermService;
import com.zhihao.system.service.SysUserService;
import com.zhihao.work.pojo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermService sysPermService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getSysUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("用户不存在");
        }
        Set<SysPermissions> permSet = sysPermService.listSysPerms(username);     // 获取用户权限
        List<String> permList = permSet.stream().map(SysPermissions::getPermissionsKey).collect(Collectors.toList());
        return new LoginUser(user, permList);
    }

}
