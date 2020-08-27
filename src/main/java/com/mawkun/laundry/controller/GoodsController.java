package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
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
    public Goods getById(Long id) {
        Goods goods = goodsServiceExt.getById(id);
        return goods!=null?goods:new Goods();
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    public Goods getByEntity(@LoginedAuth UserSession session, Goods goods) {

        return goodsServiceExt.getByEntity(goods);
    }

    @GetMapping("/list")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    public List<Goods> list(Goods goods) {
        List<Goods> goodsList = goodsServiceExt.listByEntity(goods);
        return goodsList;
    }

    @PostMapping("/insert")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    public Goods insert(Goods goods){
        goodsServiceExt.insert(goods);
        return goods;
    }

    @PostMapping("/update")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    public int update(Goods goods){
        return goodsServiceExt.update(goods);
    }

    @GetMapping("/delete")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "Long", paramType = "header")
    public int deleteOne(Long id){
        return goodsServiceExt.deleteById(id);
    }

    @GetMapping("/deleteBatch")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = goodsServiceExt.deleteByIds(ids);
        return result;
    }
}