package com.zhihao.system.service;

import com.zhihao.system.pojo.dto.SysRoleDTO;
import com.zhihao.system.pojo.vo.SysRoleVO;

import java.util.List;

public interface SysRoleService {

    /*
    * 角色列表
    * */
    List<SysRoleVO> listSysRoleVOs();

    /*
    * 新增角色
    * */
    int insertSysRole(SysRoleDTO roleDTO);

    /*
    * 修改角色
    * */
    int updateSysRole(Integer roleId, SysRoleDTO roleDTO);

    /*
    * 删除角色
    * */
    int deleteSysRole(Integer roleId);

}
