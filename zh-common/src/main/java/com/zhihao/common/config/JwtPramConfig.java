package com.zhihao.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
* 获取 classpath 资源文件键值对
* */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtPramConfig {

    private String secret;      // token 密钥

    private Integer expire;     // token 过期时间（天）

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

}
