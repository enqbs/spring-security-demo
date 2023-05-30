package com.zhihao.work.service.impl;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zhihao.common.config.JwtPramConfig;
import com.zhihao.common.constant.Constants;
import com.zhihao.common.exception.ServiceException;
import com.zhihao.common.util.JwtUtil;
import com.zhihao.common.util.RedisUtil;
import com.zhihao.work.pojo.LoginUser;
import com.zhihao.work.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JwtPramConfig jwtPramConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getToken(LoginUser loginUser) {
        return JwtUtil.createToken(Constants.USER_TOKEN, loginUser.getUserToken(), jwtPramConfig.getExpire(), jwtPramConfig.getSecret());
    }

    @Override
    public String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    @Override
    public LoginUser getLoginUser(String token) {
        String userToken = JwtUtil.getString(token, Constants.USER_TOKEN);
        String redisKey = String.format(Constants.USER_REDIS_KEY, userToken);
        return (LoginUser) redisUtil.getObject(redisKey);
    }

    @Override
    public String getNewToken(String token) {
        try {
            JwtUtil.verifierToken(token, jwtPramConfig.getSecret());
            long expireTime = JwtUtil.getExpire(token);                 // JWT 过期时间
            long currentTime = System.currentTimeMillis() / 1000L;      // 当前时间
            long oneHour = 3600L;                                       // 一小时 3600 秒
            /* 如果 jwt 过期时间小于1小时,返回新 token */
            if (expireTime - currentTime < oneHour) {
                String userToken = JwtUtil.getString(token, Constants.USER_TOKEN);
                return JwtUtil.createToken(Constants.USER_TOKEN, userToken, jwtPramConfig.getExpire(), jwtPramConfig.getSecret());
            }
            return token;
        } catch (TokenExpiredException e) {
            String userToken = JwtUtil.getString(token, Constants.USER_TOKEN);
            return JwtUtil.createToken(Constants.USER_TOKEN, userToken, jwtPramConfig.getExpire(), jwtPramConfig.getSecret());
        }
    }

    @Override
    public void verifierToken(String token) {
        try {
            JwtUtil.verifierToken(token, jwtPramConfig.getSecret());
        } catch (Exception e) {
            if (e instanceof TokenExpiredException) {
                String userToken = JwtUtil.getString(token, Constants.USER_TOKEN);
                String redisKey = String.format(Constants.USER_REDIS_KEY, userToken);
                if (!redisUtil.isExist(redisKey)) {
                    throw new ServiceException("用户信息已过期,请重新登录");
                }
            } else {
                log.error("无效的token:'{}'", token);
                throw new ServiceException("无效的token");
            }
        }
    }

}
