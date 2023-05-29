package com.zhihao.system.dao;

import com.zhihao.system.pojo.SysUser;

import java.util.List;

public interface SysUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int countSysUserByUsername(String username);

    SysUser selectByUsername(String username);

    List<SysUser> selectListByAll();

}
