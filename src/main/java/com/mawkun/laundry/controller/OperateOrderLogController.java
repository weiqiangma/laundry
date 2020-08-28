package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.entity.OperateOrderLog;
import com.mawkun.laundry.base.service.OperateOrderLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-28 21:15:45
 */
@RestController
@Api(tags={"订单日志操作接口"})
@RequestMapping("/operateOrderLog")
public class OperateOrderLogController extends BaseController {
    
    @Autowired
    private OperateOrderLogService operateOrderLogService;

    @GetMapping("/get")
    @ApiOperation(value="订单日志详情", notes="订单日志详情")
    public JsonResult getById(Long id) {
        OperateOrderLog operateOrderLog = operateOrderLogService.getById(id);
        return sendSuccess(operateOrderLog);
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="订单日志详情", notes="订单日志详情")
    public JsonResult getByEntity(OperateOrderLog operateOrderLog) {
        OperateOrderLog resultLog = operateOrderLogService.getByEntity(operateOrderLog);
        return sendSuccess(resultLog);
    }

    @GetMapping("/list")
    @ApiOperation(value="订单日志列表", notes="订单日志列表")
    public JsonResult list(OperateOrderLog operateOrderLog) {
        List<OperateOrderLog> operateOrderLogList = operateOrderLogService.listByEntity(operateOrderLog);
        return sendSuccess(operateOrderLogList);
    }

    @PostMapping("/insert")
    @ApiOperation(value="添加订单日志", notes="添加订单日志")
    public JsonResult insert(@RequestBody OperateOrderLog operateOrderLog){
        operateOrderLogService.insert(operateOrderLog);
        return sendSuccess(operateOrderLog);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑订单日志", notes="编辑订单日志")
    public JsonResult update(@RequestBody OperateOrderLog operateOrderLog){
        int result = operateOrderLogService.update(operateOrderLog);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除订单日志", notes="删除订单日志")
    public JsonResult deleteOne(Long id){
        int result = operateOrderLogService.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除订单日志", notes="批量删除订单日志")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = operateOrderLogService.deleteByIds(ids);
        return sendSuccess(result);
    }

}