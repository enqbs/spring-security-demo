package com.zhihao.app.controller.sys;

import com.zhihao.common.exception.ServiceException;
import com.zhihao.common.util.R;
import com.zhihao.system.pojo.dto.SysUserDTO;
import com.zhihao.system.pojo.dto.SysUserRoleDTO;
import com.zhihao.system.pojo.vo.SysUserVO;
import com.zhihao.system.service.SysUserRoleService;
import com.zhihao.system.service.SysUserService;
import com.zhihao.work.pojo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /*
    * 用户列表
    * */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('SYS_USER:GET')")
    public R<List<SysUserVO>> userList() {
        List<SysUserVO> userVOList = sysUserService.listSysUserVOs();
        return R.ok(userVOList);
    }

    /*
    * 修改用户(用户名/昵称/密码)
    * */
    @PutMapping
    public R<Void> userUpdate(@RequestBody SysUserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        int updateRow = sysUserService.updateSysUser(loginUser.getSysUser().getId(), userDTO);
        if (updateRow >= 1) {
            return R.ok("修改成功");
        } else {
            throw new ServiceException("修改失败");
        }
    }

    /*
    * 新增用户角色关系
    * */
    @PostMapping("/give-role")
    @PreAuthorize("hasAuthority('SYS_USER:UPDATE')")
    public R<Void> userRoleGive(@RequestBody @Valid SysUserRoleDTO userRoleDTO) {
        try {
            int insertRow = sysUserRoleService.insertUserRole(userRoleDTO.getUserId(), userRoleDTO.getRoleIdSet());
            if (insertRow >= 1) {
                return R.ok("绑定角色成功");
            } else {
                throw new ServiceException("绑定角色失败");
            }
        } catch (Exception e) {
            throw new ServiceException("绑定角色失败");
        }
    }

    /*
    * 修改用户角色关系
    * */
    @PutMapping("/give-role")
    @PreAuthorize("hasAuthority('SYS_USER:UPDATE')")
    public R<Void> userRoleUpdate(@RequestBody @Valid SysUserRoleDTO userRoleDTO) {
        int updateRow = sysUserRoleService.updateUserRole(userRoleDTO.getUserId(), userRoleDTO.getRoleIdSet());
        if (updateRow >= 1) {
            return R.ok("修改角色成功");
        } else {
            throw new ServiceException("修改角色失败");
        }
    }

    /*
    * 删除用户角色关系
    * */
    @DeleteMapping("/give-role")
    @PreAuthorize("hasAuthority('SYS_USER:UPDATE')")
    public R<Void> userRoleDelete(@RequestBody @Valid SysUserRoleDTO userRoleDTO) {
        int deleteRow = sysUserRoleService.deleteUserRole(userRoleDTO.getUserId(), userRoleDTO.getRoleIdSet());
        if (deleteRow >= 1) {
            return R.ok("删除角色成功");
        } else {
            throw new ServiceException("删除角色失败");
        }
    }

    /*
    * 删除用户
    * */
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('SYS_USER:DELETE')")
    public R<Void> userDelete(@PathVariable Integer userId) {
        int deleteRow = sysUserService.deleteSysUser(userId);
        if (deleteRow >= 1) {
            return R.ok("删除成功");
        } else {
            throw new ServiceException("删除失败");
        }
    }

}
