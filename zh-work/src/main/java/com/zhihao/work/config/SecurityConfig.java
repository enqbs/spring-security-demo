package com.zhihao.work.config;

import com.zhihao.work.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            return http
                    .csrf(AbstractHttpConfigurer::disable)                              // 关闭 csrf
                    .sessionManagement(s -> s
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // 不需要 session
                    )
                    .authorizeRequests(a -> a
                            .antMatchers("/login").anonymous()                        // 只允许未登录访问 url
                            .antMatchers("/hello").permitAll()                        // 允许匿名访问 url
                            .anyRequest().authenticated()                               // 所有接口拦截
                    )
                    .exceptionHandling(e -> e
                            .authenticationEntryPoint(authenticationEntryPoint)         // 自定义认证异常处理
                            .accessDeniedHandler(accessDeniedHandler)                   // 自定义授权异常处理
                    )
                    .addFilterBefore(jwtAuthenticationTokenFilter,                      // 自定义过滤器
                            UsernamePasswordAuthenticationFilter.class
                    )
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
