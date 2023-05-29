package com.zhihao.system.service.impl;

import com.zhihao.common.constant.Constants;
import com.zhihao.system.dao.SysRoleMapper;
import com.zhihao.system.pojo.SysRole;
import com.zhihao.system.pojo.dto.SysRoleDTO;
import com.zhihao.system.pojo.vo.SysRoleVO;
import com.zhihao.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleVO> listSysRoleVOs() {
        List<SysRole> roleList = sysRoleMapper.selectListByAll();
        return roleList.stream().map(this::sysRole2SysRoleVO).collect(Collectors.toList());
    }

    @Override
    public int insertSysRole(SysRoleDTO roleDTO) {
        SysRole role = sysRoleDTO2SysRole(roleDTO);
        return sysRoleMapper.insertSelective(role);
    }

    @Override
    public int updateSysRole(Integer roleId, SysRoleDTO roleDTO) {
        SysRole role = sysRoleDTO2SysRole(roleDTO);
        role.setId(roleId);
        return sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteSysRole(Integer roleId) {
        SysRole role = new SysRole();
        role.setId(roleId);
        role.setIsDelete(Constants.IS_DELETE);
        return sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    private SysRoleVO sysRole2SysRoleVO(SysRole role) {
        SysRoleVO roleVO = new SysRoleVO();
        BeanUtils.copyProperties(role, roleVO);
        return roleVO;
    }

    private SysRole sysRoleDTO2SysRole(SysRoleDTO roleDTO) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

}
