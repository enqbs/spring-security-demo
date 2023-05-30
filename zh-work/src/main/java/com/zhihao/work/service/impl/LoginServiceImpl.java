package com.zhihao.work.service.impl;

import com.zhihao.common.constant.Constants;
import com.zhihao.common.exception.ServiceException;
import com.zhihao.common.util.R;
import com.zhihao.common.util.RedisUtil;
import com.zhihao.common.util.UUIDUtil;
import com.zhihao.system.pojo.SysUser;
import com.zhihao.system.pojo.vo.SysUserVO;
import com.zhihao.system.service.SysUserService;
import com.zhihao.work.form.LoginForm;
import com.zhihao.work.form.RegisterForm;
import com.zhihao.work.pojo.LoginUser;
import com.zhihao.work.service.LoginService;
import com.zhihao.work.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public R<Map<String, String>> login(LoginForm form) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        /*
        * 此方法会调用 UserDetailsService 并且校验密码
        * */
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        loginUser.setUserToken(UUIDUtil.getUUID());

        cacheLoginUser(loginUser);                          // 用户信息缓存 redis
        String token = tokenService.getToken(loginUser);    // 获取 token
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return R.ok("登录成功", map);
    }

    @Override
    public R<Map<String, Object>> getUserInfo(String token) {
        String newToken = tokenService.getNewToken(token);
        LoginUser loginUser = tokenService.getLoginUser(newToken);
        SysUserVO userVO = sysUserService.getSysUserVOByUserId(loginUser.getSysUser().getId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", newToken);
        map.put("userInfo", userVO);
        return R.ok(map);
    }

    @Override
    public R<Void> register(RegisterForm form) {
        int count = sysUserService.countSysUserByUsername(form.getUsername());
        log.info("count:{}", count);
        if (count <= 0) {
            int insertRow = sysUserService.insertSysUser(buildSysUser(form));
            if (insertRow >= 1) {
                return R.ok("注册成功");
            } else {
                throw new ServiceException("注册失败");
            }
        }
        throw new ServiceException("用户已存在");
    }

    @Override
    public R<Void> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        removeCacheLoginUser(loginUser);
        return R.ok("退出成功");
    }

    private void cacheLoginUser(LoginUser loginUser) {
        String redisKey = String.format(Constants.USER_REDIS_KEY, loginUser.getUserToken());
        long cacheTimeout = 3600 * 24 * 10 * 1000L;     // 用户信息 redis 缓存10天(免登录)
        redisUtil.setObject(redisKey, loginUser, cacheTimeout);
    }

    private void removeCacheLoginUser(LoginUser loginUser) {
        String redisKey = String.format(Constants.USER_REDIS_KEY, loginUser.getUserToken());
        redisUtil.deleteObject(redisKey);
    }

    private SysUser buildSysUser(RegisterForm form) {
        SysUser user = new SysUser();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setNickName("用户:" + UUIDUtil.getUUID());
        return user;
    }

}
