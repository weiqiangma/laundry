package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.OrderFormQuery;
import com.mawkun.laundry.base.entity.OrderForm;
import com.mawkun.laundry.service.OrderFormServiceExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:45
 */
@RestController
@RequestMapping("/adm/orderForm")
@Api(tags={"订单操作接口"})
public class OrderFormController extends BaseController {
    
    @Autowired
    private OrderFormServiceExt orderFormServiceExt;

    @GetMapping("/get")
    @ApiOperation(value="根据id获取订单", notes="根据id获取订单")
    public OrderForm getById(Long id) {
        OrderForm orderForm = orderFormServiceExt.getById(id);
        return orderForm!=null?orderForm:new OrderForm();
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="根据entity获取订单", notes="根据entity获取订单")
    public JsonResult getByEntity(OrderForm orderForm) {
        OrderForm resultForm = orderFormServiceExt.getByEntity(orderForm);
        return sendSuccess(resultForm);
    }

    @GetMapping("/list")
    @ApiOperation(value="获取订单列表", notes="获取订单列表")
    public JsonResult list(OrderForm orderForm) {
        List<OrderForm> orderFormList = orderFormServiceExt.listByEntity(orderForm);
        return sendSuccess(orderFormList);
    }

    @GetMapping("pageList")
    @ApiOperation(value="订单列表分页", notes="订单列表分页")
    public JsonResult pageList(OrderFormQuery query) {
        PageInfo page = orderFormServiceExt.pageByEntity(query);
        return sendSuccess(page);
    }

    @PostMapping("/insert")
    @ApiOperation(value="添加订单", notes="添加订单")
    public JsonResult insert(OrderForm orderForm){
        orderFormServiceExt.insert(orderForm);
        return sendSuccess(orderForm);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑订单", notes="编辑订单")
    public JsonResult update(OrderForm orderForm){
        int result = orderFormServiceExt.update(orderForm);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除订单", notes="删除订单")
    public JsonResult deleteOne(Long id){
        int result = orderFormServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除订单", notes="批量删除订单")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = orderFormServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }
}