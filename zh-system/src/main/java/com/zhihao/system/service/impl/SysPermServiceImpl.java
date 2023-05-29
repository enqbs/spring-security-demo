package com.zhihao.system.service.impl;

import com.zhihao.common.constant.Constants;
import com.zhihao.system.dao.SysPermissionsMapper;
import com.zhihao.system.pojo.SysPermissions;
import com.zhihao.system.pojo.dto.SysPermissionsDTO;
import com.zhihao.system.pojo.vo.SysPermissionsVO;
import com.zhihao.system.service.SysPermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysPermServiceImpl implements SysPermService {

    @Autowired
    private SysPermissionsMapper permissionsMapper;

    @Override
    public Set<SysPermissions> listSysPerms(String username) {
        return permissionsMapper.selectSetByUsername(username);
    }

    @Override
    public List<SysPermissionsVO> listSysPermVOs() {
        List<SysPermissions> permList = permissionsMapper.selectListByAll();
        List<SysPermissionsVO> permVOList = permList.stream()
                .filter(permissions -> permissions.getParentId().equals(0))
                .map(this::sysPermissions2SysPermissionsVO).collect(Collectors.toList());
        findSubSysPermVOList(permList, permVOList);
        return permVOList;
    }

    @Override
    public List<SysPermissionsVO> listSysPermVOs(Integer parentId) {
        List<SysPermissions> permList = permissionsMapper.selectListByParentId(parentId);
        return permList.stream().map(this::sysPermissions2SysPermissionsVO).collect(Collectors.toList());
    }

    @Override
    public int insertSysPerm(SysPermissionsDTO permDTO) {
        SysPermissions perm = sysPermissionsDTO2SysPermissions(permDTO);
        return permissionsMapper.insertSelective(perm);
    }

    @Override
    public int updateSysPerm(Integer permId, SysPermissionsDTO permDTO) {
        SysPermissions perm = sysPermissionsDTO2SysPermissions(permDTO);
        perm.setId(permId);
        return permissionsMapper.updateByPrimaryKeySelective(perm);
    }

    @Override
    public int deleteSysPerm(Integer permId) {
        SysPermissions perm = new SysPermissions();
        perm.setId(permId);
        perm.setIsDelete(Constants.IS_DELETE);
        return permissionsMapper.updateByPrimaryKeySelective(perm);
    }

    private void findSubSysPermVOList(List<SysPermissions> permList, List<SysPermissionsVO> permVOList) {
        permVOList.forEach(permVO -> {
            List<SysPermissionsVO> subPermVOList = permList.stream()
                    .filter(perm -> permVO.getId().equals(perm.getParentId()))
                    .map(this::sysPermissions2SysPermissionsVO).collect(Collectors.toList());
            permVO.setSysPermissionsVOList(subPermVOList);
            findSubSysPermVOList(permList, subPermVOList);
        });
    }

    private SysPermissionsVO sysPermissions2SysPermissionsVO(SysPermissions perm) {
        SysPermissionsVO permVO = new SysPermissionsVO();
        BeanUtils.copyProperties(perm, permVO);
        return permVO;
    }

    private SysPermissions sysPermissionsDTO2SysPermissions(SysPermissionsDTO permDTO) {
        SysPermissions perm = new SysPermissions();
        BeanUtils.copyProperties(permDTO, perm);
        return perm;
    }

}
