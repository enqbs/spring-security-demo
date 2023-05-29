package com.zhihao.system.service.impl;

import com.zhihao.common.constant.Constants;
import com.zhihao.system.dao.SysUserMapper;
import com.zhihao.system.pojo.SysUser;
import com.zhihao.system.pojo.dto.SysUserDTO;
import com.zhihao.system.pojo.vo.SysUserVO;
import com.zhihao.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getSysUserByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public SysUserVO getSysUserVOByUserId(Integer userId) {
        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        return sysUser2SysUserVO(user);
    }

    @Override
    public SysUserVO getSysUserVOByUsername(String username) {
        SysUser user = sysUserMapper.selectByUsername(username);
        return sysUser2SysUserVO(user);
    }

    @Override
    public List<SysUserVO> listSysUserVOs() {
        List<SysUser> userList = sysUserMapper.selectListByAll();
        return userList.stream().map(this::sysUser2SysUserVO).collect(Collectors.toList());
    }

    @Override
    public int insertSysUser(SysUser user) {
        return sysUserMapper.insertSelective(user);
    }

    @Override
    public int countSysUserByUsername(String username) {
        return sysUserMapper.countSysUserByUsername(username);
    }

    @Override
    public int updateSysUser(Integer userId, SysUserDTO userDTO) {
        SysUser user = sysUserDTO2SysUser(userDTO);
        user.setId(userId);
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteSysUser(Integer userId) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setIsDelete(Constants.IS_DELETE);
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    private SysUser sysUserDTO2SysUser(SysUserDTO userDTO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    private SysUserVO sysUser2SysUserVO(SysUser user) {
        SysUserVO userVO = new SysUserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

}
