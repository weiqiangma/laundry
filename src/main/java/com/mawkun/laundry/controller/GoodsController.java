package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.Goods;
import com.mawkun.laundry.service.GoodsServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:02
 */
@RestController
@RequestMapping("/adm/goods")
@Api(value="商品controller",tags={"商品操作接口"})
public class GoodsController extends BaseController {
    
    @Autowired
    private GoodsServiceExt goodsServiceExt;

    @GetMapping("/get")
    @ApiOperation(value="根据id获取商品", notes="根据id获取商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "Long", paramType = "header")
    public JsonResult getById(Long id) {
        Goods goods = goodsServiceExt.getById(id);
        return sendSuccess(goods);
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="根据entity获取商品", notes="根据entity获取商品")
    public JsonResult getByEntity(Goods goods) {
        Goods resultGoods =  goodsServiceExt.getByEntity(goods);
        return sendSuccess(resultGoods);
    }

    @GetMapping("/list")
    @ApiOperation(value="根据entity获取商品list", notes="根据entity获取商品list")
    public JsonResult list(Goods goods) {
        List<Goods> goodsList = goodsServiceExt.listByEntity(goods);
        return sendSuccess(goodsList);
    }

    @PostMapping("/insert")
    @ApiOperation(value="添加商品", notes="添加商品")
    public JsonResult insert(Goods goods){
        goodsServiceExt.insert(goods);
        return sendSuccess(goods);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑商品", notes="编辑商品")
    public JsonResult update(Goods goods){
        int result = goodsServiceExt.update(goods);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除商品", notes="删除商品")
    public JsonResult deleteOne(Long id){
        int result = goodsServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除商品", notes="批量删除商品")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = goodsServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }
}