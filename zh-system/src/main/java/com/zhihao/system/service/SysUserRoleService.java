package com.zhihao.system.service;

import java.util.Set;

public interface SysUserRoleService {

    /*
    * 用户绑定角色
    * */
    int insertUserRole(Integer userId, Set<Integer> roleIdSet);

    /*
    * 用户修改绑定角色
    * */
    int updateUserRole(Integer userId, Set<Integer> roleIdSet);

    /*
    * 用户删除绑定角色
    * */
    int deleteUserRole(Integer userId, Set<Integer> roleIdSet);

}
