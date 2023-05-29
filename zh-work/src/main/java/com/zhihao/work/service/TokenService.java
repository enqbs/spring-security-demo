package com.zhihao.work.service;

import com.zhihao.work.pojo.LoginUser;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    /*
    * 创建 token
    * */
    String getToken(LoginUser loginUser);

    /*
    * 从请求头中获取 token
    * */
    String getToken(HttpServletRequest request);

    /*
    * 获取 LoginUser
    * */
    LoginUser getLoginUser(String token);

    /*
    * 获取新 token
    * */
    String getNewToken(String token);

    /*
    * 验证 token 有效性
    * */
    void verifierToken(String token);

}
