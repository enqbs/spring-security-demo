package com.zhihao.system.pojo;

public class SysRolePermissions {

    private Integer roleId;

    private Integer permissionsId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }

    @Override
    public String toString() {
        return "SysRolePermissions{" +
                "roleId=" + roleId +
                ", permissionsId=" + permissionsId +
                '}';
    }

}
