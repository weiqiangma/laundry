package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
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
    public OrderClothes getById(@PathVariable Long id) {
        OrderClothes orderClothes = orderClothesServiceExt.getById(id);
        return orderClothes!=null?orderClothes:new OrderClothes();
    }

    @RequestMapping("/get")
    public OrderClothes getByEntity(OrderClothes orderClothes) {
        return orderClothesServiceExt.getByEntity(orderClothes);
    }

    @RequestMapping("/list")
    public List<OrderClothes> list(OrderClothes orderClothes) {
        List<OrderClothes> orderClothesList = orderClothesServiceExt.listByEntity(orderClothes);
        return orderClothesList;
    }

    @RequestMapping("/insert")
    public OrderClothes insert(@RequestBody OrderClothes orderClothes){
        orderClothesServiceExt.insert(orderClothes);
        return orderClothes;
    }

    @RequestMapping("/update")
    public int update(@RequestBody OrderClothes orderClothes){
        return orderClothesServiceExt.update(orderClothes);
    }

    @RequestMapping("/delete/{id}")
    public int deleteOne(@PathVariable Long id){
        return orderClothesServiceExt.deleteById(id);
    }

    @RequestMapping("/delete")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = orderClothesServiceExt.deleteByIds(ids);
        return result;
    }

}