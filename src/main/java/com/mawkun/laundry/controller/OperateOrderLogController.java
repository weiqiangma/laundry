package com.mawkun.laundry.controller;

import com.mawkun.laundry.base.entity.OperateOrderLog;
import com.mawkun.laundry.base.service.OperateOrderLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-29 14:40:07
 */
@RestController
@Api(tags={"订单操作日志接口"})
@RequestMapping("/operateOrderLog")
public class OperateOrderLogController {
    
    @Autowired
    private OperateOrderLogService operateOrderLogService;

    @GetMapping("/get")
    @ApiOperation(value="订单日志详情", notes="订单日志详情")
    public OperateOrderLog getById(Long id) {
        OperateOrderLog operateOrderLog = operateOrderLogService.getById(id);
        return operateOrderLog!=null?operateOrderLog:new OperateOrderLog();
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="订单日志详情", notes="订单日志详情")
    public OperateOrderLog getByEntity(OperateOrderLog operateOrderLog) {
        return operateOrderLogService.getByEntity(operateOrderLog);
    }

    @GetMapping("/list")
    @ApiOperation(value="订单日志列表", notes="订单日志列表")
    public List<OperateOrderLog> list(OperateOrderLog operateOrderLog) {
        List<OperateOrderLog> operateOrderLogList = operateOrderLogService.listByEntity(operateOrderLog);
        return operateOrderLogList;
    }

    @PostMapping("/insert")
    public OperateOrderLog insert(@RequestBody OperateOrderLog operateOrderLog){
        operateOrderLogService.insert(operateOrderLog);
        return operateOrderLog;
    }

    @PutMapping("/update")
    public int update(@RequestBody OperateOrderLog operateOrderLog){
        return operateOrderLogService.update(operateOrderLog);
    }

    @DeleteMapping("/delete")
    public int deleteOne(Long id){
        return operateOrderLogService.deleteById(id);
    }

    @DeleteMapping("/deleteBatch")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = operateOrderLogService.deleteByIds(ids);
        return result;
    }

}