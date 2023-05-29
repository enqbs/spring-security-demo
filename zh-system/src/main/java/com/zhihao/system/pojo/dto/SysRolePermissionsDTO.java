package com.zhihao.system.pojo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SysRolePermissionsDTO {

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    @NotEmpty(message = "权限ID不能为空")
    private Set<Integer> permIdSet;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Set<Integer> getPermIdSet() {
        return permIdSet;
    }

    public void setPermIdSet(Set<Integer> permIdSet) {
        this.permIdSet = permIdSet;
    }

}
