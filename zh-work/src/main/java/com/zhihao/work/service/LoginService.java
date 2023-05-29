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
    * 刷新 JWT
    * */
    R<Map<String, String>> refreshToken(String token);

    /*
    * 注册
    * */
    R<Void> register(RegisterForm form);

    /*
    * 退出
    * */
    R<Void> logout();

}
