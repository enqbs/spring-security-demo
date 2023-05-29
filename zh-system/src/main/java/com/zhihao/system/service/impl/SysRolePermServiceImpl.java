package com.zhihao.system.service.impl;

import com.zhihao.system.dao.SysRolePermissionsMapper;
import com.zhihao.system.pojo.SysRolePermissions;
import com.zhihao.system.service.SysRolePermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SysRolePermServiceImpl implements SysRolePermService {

    @Autowired
    private SysRolePermissionsMapper sysRolePermissionsMapper;

    @Override
    public int insertRolePerm(Integer roleId, Set<Integer> premIdSet) {
        List<SysRolePermissions> rolePermList = buildSysRolePermissionsList(roleId, premIdSet);
        return sysRolePermissionsMapper.batchInsert(rolePermList);
    }

    @Override
    public int updateRolePerm(Integer roleId, Set<Integer> premIdSet) {
        sysRolePermissionsMapper.deleteByRoleId(roleId);
        return insertRolePerm(roleId, premIdSet);
    }

    @Override
    public int deleteRolePerm(Integer roleId, Set<Integer> premIdSet) {
        List<SysRolePermissions> rolePermList = buildSysRolePermissionsList(roleId, premIdSet);
        return sysRolePermissionsMapper.batchDelete(rolePermList);
    }

    private List<SysRolePermissions> buildSysRolePermissionsList(Integer roleId, Set<Integer> permIdSet) {
        List<SysRolePermissions> rolePermList = new ArrayList<>();
        permIdSet.forEach(permId -> {
            SysRolePermissions rolePerm = buildSysRolePermissions(roleId, permId);
            rolePermList.add(rolePerm);
        });
        return rolePermList;
    }

    private SysRolePermissions buildSysRolePermissions(Integer roleId, Integer permId) {
        SysRolePermissions rolePerm = new SysRolePermissions();
        rolePerm.setRoleId(roleId);
        rolePerm.setPermissionsId(permId);
        return rolePerm;
    }

}
