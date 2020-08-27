package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.entity.OrderForm;
import com.mawkun.laundry.service.OrderFormServiceExt;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:45
 */
@RestController
@RequestMapping("/adm/orderForm")
@Api(value="订单controller",tags={"订单操作接口"})
public class OrderFormController extends BaseController {
    
    @Autowired
    private OrderFormServiceExt orderFormServiceExt;

    @RequestMapping("/get/{id}")
    public OrderForm getById(@PathVariable Long id) {
        OrderForm orderForm = orderFormServiceExt.getById(id);
        return orderForm!=null?orderForm:new OrderForm();
    }

    @RequestMapping("/get")
    public JsonResult getByEntity(OrderForm orderForm) {
        OrderForm resultForm = orderFormServiceExt.getByEntity(orderForm);
        return sendSuccess(resultForm);
    }

    @RequestMapping("/list")
    public JsonResult list(OrderForm orderForm) {
        List<OrderForm> orderFormList = orderFormServiceExt.listByEntity(orderForm);
        return sendSuccess(orderFormList);
    }

    @RequestMapping("/insert")
    public JsonResult insert(@RequestBody OrderForm orderForm){
        orderFormServiceExt.insert(orderForm);
        return sendSuccess(orderForm);
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody OrderForm orderForm){
        int result = orderFormServiceExt.update(orderForm);
        return sendSuccess(result);
    }

    @RequestMapping("/delete/{id}")
    public JsonResult deleteOne(@PathVariable Long id){
        int result = orderFormServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @RequestMapping("/delete")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = orderFormServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }

}