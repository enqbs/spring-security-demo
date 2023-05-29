package com.zhihao.system.pojo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SysPermissionsDTO {

    @NotNull(message = "权限所属分类不能为空")
    private Integer parentId;

    @NotBlank(message = "权限名称不能为空")
    private String permissionsName;

    private String path;

    private String permissionsKey;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermissionsKey() {
        return permissionsKey;
    }

    public void setPermissionsKey(String permissionsKey) {
        this.permissionsKey = permissionsKey;
    }

}
