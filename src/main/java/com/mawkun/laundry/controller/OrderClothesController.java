package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.entity.OrderClothes;
import com.mawkun.laundry.service.OrderClothesServiceExt;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:30
 */
@RestController
@RequestMapping("/adm/orderClothes")
public class OrderClothesController extends BaseController {
    
    @Autowired
    private OrderClothesServiceExt orderClothesServiceExt;

    @RequestMapping("/get/{id}")
    public JsonResult getById(@PathVariable Long id) {
        OrderClothes orderClothes = orderClothesServiceExt.getById(id);
        return sendSuccess(orderClothes);
    }

    @RequestMapping("/get")
    public JsonResult getByEntity(OrderClothes orderClothes) {
        OrderClothes resultOrderClothes = orderClothesServiceExt.getByEntity(orderClothes);
        return sendSuccess(resultOrderClothes);
    }

    @RequestMapping("/list")
    public JsonResult list(OrderClothes orderClothes) {
        List<OrderClothes> orderClothesList = orderClothesServiceExt.listByEntity(orderClothes);
        return sendSuccess(orderClothesList);
    }

    @RequestMapping("/insert")
    public JsonResult insert(OrderClothes orderClothes){
        orderClothesServiceExt.insert(orderClothes);
        return sendSuccess(orderClothes);
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody OrderClothes orderClothes){
        int result = orderClothesServiceExt.update(orderClothes);
        return sendSuccess(result);
    }

    @RequestMapping("/delete/{id}")
    public JsonResult deleteOne(@PathVariable Long id){
        int result = orderClothesServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @RequestMapping("/delete")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = orderClothesServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }

}