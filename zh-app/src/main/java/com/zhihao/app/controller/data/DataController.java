package com.zhihao.app.controller.data;

import com.zhihao.common.exception.ServiceException;
import com.zhihao.common.util.R;
import com.zhihao.system.pojo.dto.DataDTO;
import com.zhihao.system.pojo.vo.DataVO;
import com.zhihao.system.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    /*
    * 测试数据列表
    * */
    @GetMapping("/list")
    public R<List<DataVO>> dataList() {
        List<DataVO> dataVOList = dataService.getDataVOList();
        return R.ok(dataVOList);
    }

    /*
    * 新增测试数据
    * */
    @PostMapping
    @PreAuthorize("hasAuthority('DATA:ADD')")
    public R<Void> dataAdd(@RequestBody @Valid DataDTO dataDTO) {
        int insertRow = dataService.insertData(dataDTO);
        if (insertRow >= 1) {
            return R.ok("新增成功");
        } else {
            throw new ServiceException("新增失败");
        }
    }

    /*
    * 修改测试数据
    * */
    @PutMapping("/{dataId}")
    @PreAuthorize("hasAuthority('DATA:UPDATE')")
    public R<Void> dataUpdate(@PathVariable Integer dataId, @RequestBody @Valid DataDTO dataDTO) {
        int updateRow = dataService.updateData(dataId, dataDTO);
        if (updateRow >= 1) {
            return R.ok("修改成功");
        } else {
            throw new ServiceException("修改失败");
        }
    }

    /*
    * 删除测试数据
    * */
    @DeleteMapping("/{dataId}")
    @PreAuthorize("hasAuthority('DATA:DELETE')")
    public R<Void> dataDelete(@PathVariable Integer dataId) {
        int deleteRow = dataService.deleteData(dataId);
        if (deleteRow >= 1) {
            return R.ok("删除成功");
        } else {
            throw new ServiceException("删除失败");
        }
    }

}
