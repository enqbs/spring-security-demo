package com.zhihao.system.service;

import com.zhihao.system.pojo.dto.DataDTO;
import com.zhihao.system.pojo.vo.DataVO;

import java.util.List;

public interface DataService {

    /*
    * 测试数据列表
    * */
    List<DataVO> getDataVOList();

    /*
    * 新增测试数据
    * */
    int insertData(DataDTO dataDTO);

    /*
    * 修改测试数据
    * */
    int updateData(Integer dataId, DataDTO dataDTO);

    /*
    * 删除测试数据
    * */
    int deleteData(Integer dataId);

}
