package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.data.ShopIncomeData;
import com.mawkun.laundry.base.data.query.ShopIncomeQuery;
import com.mawkun.laundry.base.entity.Shop;
import com.mawkun.laundry.service.ShopServiceExt;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:58
 */
@RestController
@RequestMapping("/adm/shop")
public class ShopController extends BaseController {
    
    @Autowired
    private ShopServiceExt shopServiceExt;

    @RequestMapping("/get/{id}")
    public Shop getById(@PathVariable Long id) {
        Shop shop = shopServiceExt.getById(id);
        return shop!=null?shop:new Shop();
    }

    @RequestMapping("/get")
    public JsonResult getByEntity(Shop shop) {
        Shop resultShop = shopServiceExt.getByEntity(shop);
        return sendSuccess(resultShop);
    }

    @RequestMapping("/list")
    public JsonResult list(Shop shop) {
        List<Shop> shopList = shopServiceExt.listByEntity(shop);
        return sendSuccess(shopList);
    }

    @RequestMapping("/insert")
    public JsonResult insert(Shop shop, MultipartFile[] file){
        int result = shopServiceExt.insertWithPic(shop, file);
        return sendSuccess(result);
    }

    @RequestMapping("/update")
    public JsonResult update(Shop shop){
        int result = shopServiceExt.update(shop);
        return sendSuccess(result);
    }

    @RequestMapping("/delete")
    public JsonResult deleteOne(Long id){
        int result = shopServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @RequestMapping("/deleteBatch")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = shopServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }

    /**
     * 统计门店收入
     * @param query
     * @return
     */
    @RequestMapping("/statsShopIncome")
    public JsonResult statsShopIncome(ShopIncomeQuery query) {
        List<ShopIncomeData> list = shopServiceExt.statsShopIncome(query);
        return sendSuccess(list);
    }

}