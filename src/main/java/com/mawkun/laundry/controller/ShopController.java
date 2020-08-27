package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.data.ShopIncomeData;
import com.mawkun.laundry.base.data.query.ShopIncomeQuery;
import com.mawkun.laundry.base.entity.Shop;
import com.mawkun.laundry.service.ShopServiceExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags={"门店操作接口"})
public class ShopController extends BaseController {
    
    @Autowired
    private ShopServiceExt shopServiceExt;

    @RequestMapping("/get")
    @ApiOperation(value="门店详情", notes="门店详情")
    public Shop getById(Long id) {
        Shop shop = shopServiceExt.getById(id);
        return shop!=null?shop:new Shop();
    }

    @RequestMapping("/getByEntity")
    @ApiOperation(value="门店详情", notes="门店详情")
    public JsonResult getByEntity(Shop shop) {
        Shop resultShop = shopServiceExt.getByEntity(shop);
        return sendSuccess(resultShop);
    }

    @RequestMapping("/list")
    @ApiOperation(value="门店列表", notes="门店列表")
    public JsonResult list(Shop shop) {
        List<Shop> shopList = shopServiceExt.listByEntity(shop);
        return sendSuccess(shopList);
    }

    @RequestMapping("/insert")
    @ApiOperation(value="添加门店", notes="添加门店")
    public JsonResult insert(Shop shop, MultipartFile[] file){
        int result = shopServiceExt.insertWithPic(shop, file);
        return sendSuccess(result);
    }

    @RequestMapping("/update")
    @ApiOperation(value="编辑门店", notes="编辑门店")
    public JsonResult update(Shop shop){
        int result = shopServiceExt.update(shop);
        return sendSuccess(result);
    }

    @RequestMapping("/delete")
    @ApiOperation(value="删除门店", notes="删除门店")
    public JsonResult deleteOne(Long id){
        int result = shopServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @RequestMapping("/deleteBatch")
    @ApiOperation(value="批量删除门店", notes="批量删除门店")
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
    @ApiOperation(value="统计门店收入", notes="统计门店收入")
    public JsonResult statsShopIncome(ShopIncomeQuery query) {
        List<ShopIncomeData> list = shopServiceExt.statsShopIncome(query);
        return sendSuccess(list);
    }

}