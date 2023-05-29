package com.zhihao.system.pojo.vo;

import java.util.Date;
import java.util.List;

public class SysPermissionsVO {

    private Integer id;

    private Integer parentId;

    private String permissionsName;

    private String path;

    private String permissionsKey;

    private Date createTime;

    private Date updateTime;

    private List<SysPermissionsVO> sysPermissionsVOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<SysPermissionsVO> getSysPermissionsVOList() {
        return sysPermissionsVOList;
    }

    public void setSysPermissionsVOList(List<SysPermissionsVO> sysPermissionsVOList) {
        this.sysPermissionsVOList = sysPermissionsVOList;
    }

    @Override
    public String toString() {
        return "SysPermissionsVO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", permissionsName='" + permissionsName + '\'' +
                ", path='" + path + '\'' +
                ", permissionsKey='" + permissionsKey + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sysPermissionsVOList=" + sysPermissionsVOList +
                '}';
    }

}
