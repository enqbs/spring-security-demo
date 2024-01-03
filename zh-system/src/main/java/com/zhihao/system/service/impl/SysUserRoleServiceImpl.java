package com.zhihao.system.service.impl;

import com.zhihao.system.dao.SysUserRoleMapper;
import com.zhihao.system.pojo.SysUserRole;
import com.zhihao.system.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int insertUserRole(Integer userId, Set<Integer> roleIdSet) {
        List<SysUserRole> userRoleList = buildSysUserRoleList(userId, roleIdSet);
        return sysUserRoleMapper.batchInsert(userRoleList);
    }

    @Override
    public int updateUserRole(Integer userId, Set<Integer> roleIdSet) {
        sysUserRoleMapper.deleteByUserId(userId);
        return insertUserRole(userId, roleIdSet);
    }

    @Override
    public int deleteUserRole(Integer userId, Set<Integer> roleIdSet) {
        List<SysUserRole> userRoleList = buildSysUserRoleList(userId, roleIdSet);
        return sysUserRoleMapper.batchDelete(userRoleList);
    }

    private List<SysUserRole> buildSysUserRoleList(Integer userId, Set<Integer> roleIdSet) {
        return roleIdSet.stream().map(r -> buildSysUserRole(userId, r)).collect(Collectors.toList());
    }

    private SysUserRole buildSysUserRole(Integer userId, Integer roleId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRole;
    }

}
