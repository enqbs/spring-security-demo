package com.zhihao.work.service;

import com.zhihao.common.util.R;
import com.zhihao.work.form.LoginForm;
import com.zhihao.work.form.RegisterForm;

import java.util.Map;

public interface LoginService {

    /*
    * 登录
    * */
    R<Map<String, String>> login(LoginForm form);

    /*
    * 登录用户信息、无状态刷新 token
    * */
    R<Map<String, Object>> getUserInfo(String token);

    /*
    * 注册
    * */
    R<Void> register(RegisterForm form);

    /*
    * 退出
    * */
    R<Void> logout();

}
