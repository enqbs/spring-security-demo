package com.zhihao.system.dao;

import com.zhihao.system.pojo.SysRolePermissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionsMapper {

    int deleteByPrimaryKey(SysRolePermissions key);

    int deleteByRoleId(Integer roleId);

    int insert(SysRolePermissions record);

    int insertSelective(SysRolePermissions record);

    int batchInsert(@Param("rolePermList") List<SysRolePermissions> rolePermList);

    int batchDelete(@Param("rolePermList") List<SysRolePermissions> rolePermList);

}
