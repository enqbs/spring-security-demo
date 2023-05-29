package com.zhihao.system.service.impl;

import com.zhihao.common.constant.Constants;
import com.zhihao.system.dao.DataMapper;
import com.zhihao.system.pojo.Data;
import com.zhihao.system.pojo.dto.DataDTO;
import com.zhihao.system.pojo.vo.DataVO;
import com.zhihao.system.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<DataVO> getDataVOList() {
        List<Data> dataList = dataMapper.selectListAll();
        return dataList.stream().map(this::data2DataVO).collect(Collectors.toList());
    }

    @Override
    public int insertData(DataDTO dataDTO) {
        Data data = new Data();
        data.setDataName(dataDTO.getDataName());
        return dataMapper.insertSelective(data);
    }

    @Override
    public int updateData(Integer dataId, DataDTO dataDTO) {
        Data data = new Data();
        data.setId(dataId);
        data.setDataName(dataDTO.getDataName());
        return dataMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int deleteData(Integer dataId) {
        Data data = new Data();
        data.setId(dataId);
        data.setIsDelete(Constants.IS_DELETE);
        return dataMapper.updateByPrimaryKeySelective(data);
    }

    private DataVO data2DataVO(Data data) {
        DataVO dataVO = new DataVO();
        BeanUtils.copyProperties(data, dataVO);
        return dataVO;
    }

}
