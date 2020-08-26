package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.Goods;
import com.mawkun.laundry.service.GoodsServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:02
 */
@RestController
@RequestMapping("/adm/goods")
public class GoodsController extends BaseController {
    
    @Autowired
    private GoodsServiceExt goodsServiceExt;

    @RequestMapping("/get/{id}")
    public Goods getById(@PathVariable Long id) {
        Goods goods = goodsServiceExt.getById(id);
        return goods!=null?goods:new Goods();
    }

    @RequestMapping("/get")
    public Goods getByEntity(@LoginedAuth UserSession session, Goods goods) {

        return goodsServiceExt.getByEntity(goods);
    }

    @RequestMapping("/list")
    public List<Goods> list(Goods goods) {
        List<Goods> goodsList = goodsServiceExt.listByEntity(goods);
        return goodsList;
    }

    @RequestMapping("/insert")
    public Goods insert(@RequestBody Goods goods){
        goodsServiceExt.insert(goods);
        return goods;
    }

    @RequestMapping("/update")
    public int update(@RequestBody Goods goods){
        return goodsServiceExt.update(goods);
    }

    @RequestMapping("/delete/{id}")
    public int deleteOne(@PathVariable Long id){
        return goodsServiceExt.deleteById(id);
    }

    @RequestMapping("/delete")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = goodsServiceExt.deleteByIds(ids);
        return result;
    }
}