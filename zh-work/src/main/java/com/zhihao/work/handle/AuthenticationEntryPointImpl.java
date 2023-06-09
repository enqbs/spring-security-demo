package com.zhihao.work.handle;

import com.zhihao.common.util.GsonUtil;
import com.zhihao.common.util.R;
import com.zhihao.common.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.warn("请求地址:'{}',认证异常,msg:'{}'", request.getRequestURI(),e.getMessage());
        String resultJson = GsonUtil.obj2Json(R.error(HttpStatus.SC_UNAUTHORIZED, "认证失败,请登录"));
        WebUtil.renderString(response, HttpStatus.SC_UNAUTHORIZED, resultJson);
    }

}
