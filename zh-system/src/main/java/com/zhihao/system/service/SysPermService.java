package com.zhihao.system.service;

import com.zhihao.system.pojo.SysPermissions;
import com.zhihao.system.pojo.dto.SysPermissionsDTO;
import com.zhihao.system.pojo.vo.SysPermissionsVO;

import java.util.List;
import java.util.Set;

public interface SysPermService {

    /*
    * 获取用户权限
    * */
    Set<SysPermissions> listSysPerms(String username);

    /*
     * 权限列表(所有)
     * */
    List<SysPermissionsVO> listSysPermVOs();

    /*
     * 权限列表(分类)
     * */
    List<SysPermissionsVO> listSysPermVOs(Integer parentId);

    /*
    * 新增权限
    * */
    int insertSysPerm(SysPermissionsDTO permDTO);

    /*
    * 修改权限
    * */
    int updateSysPerm(Integer permId, SysPermissionsDTO permDTO);

    /*
    * 删除权限
    * */
    int deleteSysPerm(Integer permId);

}
