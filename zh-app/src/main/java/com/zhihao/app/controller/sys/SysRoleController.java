package com.zhihao.app.controller.sys;

import com.zhihao.common.exception.ServiceException;
import com.zhihao.common.util.R;
import com.zhihao.system.pojo.dto.SysRoleDTO;
import com.zhihao.system.pojo.dto.SysRolePermissionsDTO;
import com.zhihao.system.pojo.vo.SysRoleVO;
import com.zhihao.system.service.SysRolePermService;
import com.zhihao.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermService sysRolePermService;

    /*
    * 角色列表
    * */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('SYS_ROLE:GET')")
    public R<List<SysRoleVO>> roleList() {
        List<SysRoleVO> roleVOList = sysRoleService.listSysRoleVOs();
        return R.ok(roleVOList);
    }

    /*
    * 新增角色
    * */
    @PostMapping
    @PreAuthorize("hasAuthority('SYS_ROLE:ADD')")
    public R<Void> roleAdd(@RequestBody @Valid SysRoleDTO roleDTO) {
        int insertRow = sysRoleService.insertSysRole(roleDTO);
        if (insertRow >= 1) {
            return R.ok("新增成功");
        } else {
            throw new ServiceException("新增失败");
        }
    }

    /*
    * 修改角色
    * */
    @PutMapping("/{roleId}")
    @PreAuthorize("hasAuthority('SYS_ROLE:UPDATE')")
    public R<Void> roleUpdate(@PathVariable Integer roleId, @RequestBody @Valid SysRoleDTO roleDTO) {
        int updateRow = sysRoleService.updateSysRole(roleId, roleDTO);
        if (updateRow >= 1) {
            return R.ok("修改成功");
        } else {
            throw new ServiceException("修改失败");
        }
    }

    /*
    * 新增角色权限关系
    * */
    @PostMapping("/give-perm")
    @PreAuthorize("hasAuthority('SYS_ROLE:UPDATE')")
    public R<Void> rolePermGive(@RequestBody @Valid SysRolePermissionsDTO rolePermDTO) {
        try {
            int insertRow = sysRolePermService.insertRolePerm(rolePermDTO.getRoleId(), rolePermDTO.getPermIdSet());
            if (insertRow >= 1) {
                return R.ok("设置权限成功");
            } else {
                throw new ServiceException("设置权限失败");
            }
        } catch (Exception e) {
            throw new ServiceException("设置权限失败");
        }
    }

    /*
    * 修改角色权限关系
    * */
    @PutMapping("/give-perm")
    @PreAuthorize("hasAuthority('SYS_ROLE:UPDATE')")
    public R<Void> rolePermUpdate(@RequestBody @Valid SysRolePermissionsDTO rolePermDTO) {
        int updateRow = sysRolePermService.updateRolePerm(rolePermDTO.getRoleId(), rolePermDTO.getPermIdSet());
        if (updateRow >= 1) {
            return R.ok("修改权限成功");
        } else {
            throw new ServiceException("修改权限失败");
        }
    }

    /*
    * 删除角色权限关系
    * */
    @DeleteMapping("/give-perm")
    @PreAuthorize("hasAuthority('SYS_ROLE:UPDATE')")
    public R<Void> rolePermDelete(@RequestBody @Valid SysRolePermissionsDTO rolePermDTO) {
        int deleteRow = sysRolePermService.deleteRolePerm(rolePermDTO.getRoleId(), rolePermDTO.getPermIdSet());
        if (deleteRow >= 1) {
            return R.ok("删除权限成功");
        } else {
            throw new ServiceException("删除权限失败");
        }
    }

    /*
    * 删除角色
    * */
    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('SYS_ROLE:DELETE')")
    public R<Void> roleDelete(@PathVariable Integer roleId) {
        int deleteRow = sysRoleService.deleteSysRole(roleId);
        if (deleteRow >= 1) {
            return R.ok("删除成功");
        } else {
            throw new ServiceException("删除失败");
        }
    }

}
