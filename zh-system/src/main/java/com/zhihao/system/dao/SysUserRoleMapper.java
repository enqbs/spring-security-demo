package com.zhihao.system.dao;

import com.zhihao.system.pojo.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper {

    int deleteByPrimaryKey(SysUserRole key);

    int deleteByUserId(Integer userId);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    int batchInsert(@Param("userRoleList") List<SysUserRole> userRoleList);

    int batchDelete(@Param("userRoleList") List<SysUserRole> userRoleList);

}
