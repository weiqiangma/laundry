package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.entity.SysParam;
import com.mawkun.laundry.base.service.SysParamService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-09-02 20:40:05
 */
@RestController
@Api(tags={"系统参数操作接口"})
@RequestMapping("/sysParam")
public class SysParamController extends BaseController {
    
    @Autowired
    private SysParamService sysParamService;

    @GetMapping("/get")
    public JsonResult getById(Long id) {
        SysParam sysParam = sysParamService.getById(id);
        return sendSuccess(sysParam);
    }

    @GetMapping("/list")
    public JsonResult list(SysParam sysParam) {
        List<SysParam> sysParamList = sysParamService.listByEntity(sysParam);
        return sendSuccess(sysParamList);
    }

    @PostMapping("/insert")
    public JsonResult insert(SysParam sysParam){
        sysParamService.insert(sysParam);
        return sendSuccess(sysParam);
    }

    @PutMapping("/update")
    public JsonResult update(SysParam sysParam){
        int result = sysParamService.update(sysParam);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    public JsonResult deleteOne(Long id){
        int result = sysParamService.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = sysParamService.deleteByIds(ids);
        return result;
    }

}