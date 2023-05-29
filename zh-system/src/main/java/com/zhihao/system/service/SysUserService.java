package com.zhihao.system.service;

import com.zhihao.system.pojo.SysUser;
import com.zhihao.system.pojo.dto.SysUserDTO;
import com.zhihao.system.pojo.vo.SysUserVO;

import java.util.List;

public interface SysUserService {

    SysUser getSysUserByUsername(String username);

    SysUserVO getSysUserVOByUserId(Integer userId);

    SysUserVO getSysUserVOByUsername(String username);

    List<SysUserVO> listSysUserVOs();

    int insertSysUser(SysUser user);

    int countSysUserByUsername(String username);

    int updateSysUser(Integer userId, SysUserDTO userDTO);

    int deleteSysUser(Integer userId);

}
