package com.zhihao.system.pojo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SysUserRoleDTO {

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    @NotEmpty(message = "角色ID不能为空")
    private Set<Integer> roleIdSet;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<Integer> getRoleIdSet() {
        return roleIdSet;
    }

    public void setRoleIdSet(Set<Integer> roleIdSet) {
        this.roleIdSet = roleIdSet;
    }

}
