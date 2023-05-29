package com.zhihao.system.dao;

import com.zhihao.system.pojo.SysPermissions;

import java.util.List;
import java.util.Set;

public interface SysPermissionsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysPermissions record);

    int insertSelective(SysPermissions record);

    SysPermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermissions record);

    int updateByPrimaryKey(SysPermissions record);

    Set<SysPermissions> selectSetByUsername(String username);

    List<SysPermissions> selectListByAll();

    List<SysPermissions> selectListByParentId(Integer parentId);

}
