package com.zhihao.app.controller.sys;

import com.zhihao.common.exception.ServiceException;
import com.zhihao.common.util.R;
import com.zhihao.system.pojo.dto.SysPermissionsDTO;
import com.zhihao.system.pojo.vo.SysPermissionsVO;
import com.zhihao.system.service.SysPermService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/perm")
public class SysPermController {

    @Autowired
    private SysPermService sysPermService;

    /*
    * 权限列表(所有)
    * */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('SYS_PERM:GET')")
    public R<List<SysPermissionsVO>> permListByAll() {
        List<SysPermissionsVO> permVOList = sysPermService.listSysPermVOs();
        return R.ok(permVOList);
    }

    /*
    * 权限列表(分类)
    * */
    @GetMapping("/category-list")
    public R<List<SysPermissionsVO>> permListByParentId(@RequestParam(required = false) Integer parentId) {
        List<SysPermissionsVO> permVOList = sysPermService.listSysPermVOs(parentId);
        return R.ok(permVOList);
    }

    /*
    * 新增权限
    * */
    @PostMapping
    @PreAuthorize("hasAuthority('SYS_PERM:ADD')")
    public R<Void> premAdd(@RequestBody @Valid SysPermissionsDTO permDTO) {
        int insertRow = sysPermService.insertSysPerm(permDTO);
        if (insertRow >= 1) {
            return R.ok("新增成功");
        } else {
            throw new ServiceException("新增失败");
        }
    }

    /*
    * 修改权限
    * */
    @PutMapping("/{permId}")
    @PreAuthorize("hasAuthority('SYS_PERM:UPDATE')")
    public R<Void> premUpdate(@PathVariable Integer permId, @RequestBody @Valid SysPermissionsDTO permDTO) {
        int updateRow = sysPermService.updateSysPerm(permId, permDTO);
        if (updateRow >= 1) {
            return R.ok("修改成功");
        } else {
            throw new ServiceException("修改失败");
        }
    }

    /*
    * 删除权限
    * */
    @DeleteMapping("/{permId}")
    @PreAuthorize("hasAuthority('SYS_PERM:DELETE')")
    public R<Void> premDelete(@PathVariable Integer permId) {
        int deleteRow = sysPermService.deleteSysPerm(permId);
        if (deleteRow >= 1) {
            return R.ok("删除成功");
        } else {
            throw new ServiceException("删除失败");
        }
    }

}
