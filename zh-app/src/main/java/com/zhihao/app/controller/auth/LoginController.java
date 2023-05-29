package com.zhihao.app.controller.auth;

import com.zhihao.common.util.R;
import com.zhihao.work.form.LoginForm;
import com.zhihao.work.form.RegisterForm;
import com.zhihao.work.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*
    * 登录
    * */
    @PostMapping("/login")
    public R<Map<String, String>> login(@RequestBody @Valid LoginForm form) {
        return loginService.login(form);
    }

    /*
    * 刷新(续期) Token
    * */
    @PostMapping("/refresh-token")
    public R<Map<String, String>> refreshToken(@RequestHeader String token) {
        return loginService.refreshToken(token);
    }

    /*
    * 注册
    * */
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('SYS_USER:ADD')")
    public R<Void> register(@RequestBody @Valid RegisterForm form) {
        return loginService.register(form);
    }

    /*
    * 退出
    * */
    @PostMapping("/sign-out")
    public R<Void> logout() {
        return loginService.logout();
    }

}
