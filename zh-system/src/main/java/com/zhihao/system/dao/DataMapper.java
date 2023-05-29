package com.zhihao.system.dao;

import com.zhihao.system.pojo.Data;

import java.util.List;

public interface DataMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    Data selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);

    List<Data> selectListAll();

}
