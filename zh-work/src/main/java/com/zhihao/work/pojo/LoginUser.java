package com.zhihao.work.pojo;

import com.google.gson.annotations.Expose;
import com.zhihao.system.pojo.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoginUser implements UserDetails {

    private String userToken;

    private SysUser sysUser;

    private List<String> permissions;

    @Expose(serialize = false, deserialize = false)
    private List<SimpleGrantedAuthority> simpleGrantedAuthorityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (!CollectionUtils.isEmpty(permissions)) {
            simpleGrantedAuthorityList = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public LoginUser() {
    }

    public LoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getSimpleGrantedAuthorityList() {
        return simpleGrantedAuthorityList;
    }

    public void setSimpleGrantedAuthorityList(List<SimpleGrantedAuthority> simpleGrantedAuthorityList) {
        this.simpleGrantedAuthorityList = simpleGrantedAuthorityList;
    }

}
