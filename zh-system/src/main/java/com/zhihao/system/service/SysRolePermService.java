package com.zhihao.system.service;

import java.util.Set;

public interface SysRolePermService {

    /*
    * 新增角色权限关系
    * */
    int insertRolePerm(Integer roleId, Set<Integer> premIdSet);

    /*
    * 修改角色权限关系
    * */
    int updateRolePerm(Integer roleId, Set<Integer> premIdSet);

    /*
    * 删除角色权限关系
    * */
    int deleteRolePerm(Integer roleId, Set<Integer> premIdSet);

}
