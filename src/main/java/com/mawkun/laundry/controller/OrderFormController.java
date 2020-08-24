package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import com.mawkun.laundry.base.entity.OrderForm;
import com.mawkun.laundry.service.OrderFormServiceExt;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:45
 */
@RestController
@RequestMapping("/adm/orderForm")
public class OrderFormController extends BaseController {
    
    @Autowired
    private OrderFormServiceExt orderFormServiceExt;

    @RequestMapping("/get/{id}")
    public OrderForm getById(@PathVariable Long id) {
        OrderForm orderForm = orderFormServiceExt.getById(id);
        return orderForm!=null?orderForm:new OrderForm();
    }

    @RequestMapping("/get")
    public OrderForm getByEntity(OrderForm orderForm) {
        return orderFormServiceExt.getByEntity(orderForm);
    }

    @RequestMapping("/list")
    public List<OrderForm> list(OrderForm orderForm) {
        List<OrderForm> orderFormList = orderFormServiceExt.listByEntity(orderForm);
        return orderFormList;
    }

    @RequestMapping("/insert")
    public OrderForm insert(@RequestBody OrderForm orderForm){
        orderFormServiceExt.insert(orderForm);
        return orderForm;
    }

    @RequestMapping("/update")
    public int update(@RequestBody OrderForm orderForm){
        return orderFormServiceExt.update(orderForm);
    }

    @RequestMapping("/delete/{id}")
    public int deleteOne(@PathVariable Long id){
        return orderFormServiceExt.deleteById(id);
    }

    @RequestMapping("/delete")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = orderFormServiceExt.deleteByIds(ids);
        return result;
    }

}