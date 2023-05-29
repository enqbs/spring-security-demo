package com.zhihao.system.pojo.dto;

import javax.validation.constraints.NotBlank;

public class DataDTO {

    @NotBlank(message = "测试数据不能为空")
    private String dataName;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

}
