package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.KindQuery;
import com.mawkun.laundry.base.entity.Kind;
import com.mawkun.laundry.service.KindServiceExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:16
 */
@RestController
@RequestMapping("/adm/kind")
@Api(tags={"商品类型操作接口"})
public class KindController extends BaseController {
    
    @Autowired
    private KindServiceExt kindServiceExt;

    @GetMapping("/get")
    @ApiOperation(value="商品类型详情", notes="商品类型详情")
    public JsonResult getById(Long id) {
        Kind kind = kindServiceExt.getById(id);
        return sendSuccess(kind);
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="商品类型详情", notes="商品类型详情")
    public JsonResult getByEntity(Kind kind) {
        Kind kindResult = kindServiceExt.getByEntity(kind);
        return sendSuccess(kindResult);
    }

    @GetMapping("/list")
    @ApiOperation(value="商品类型列表", notes="商品类型列表")
    public JsonResult list(Kind kind) {
        List<Kind> kindList = kindServiceExt.listByEntity(kind);
        return sendSuccess(kindList);
    }

    @GetMapping("/pageList")
    @ApiOperation(value="商品类型列表分页", notes="商品类型列表分页")
    public JsonResult pageList(KindQuery query) {
        PageInfo page = kindServiceExt.pageByEntity(query);
        return sendSuccess(page);
    }

    @PostMapping("/insert")
    @ApiOperation(value="添加商品类型", notes="添加商品类型")
    public JsonResult insert(Kind kind){
        kindServiceExt.insert(kind);
        return sendSuccess(kind);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑商品类型", notes="编辑商品类型")
    public JsonResult update(Kind kind){
        int result = kindServiceExt.update(kind);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除商品类型", notes="删除商品类型")
    public JsonResult deleteOne(Long id){
        int result = kindServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除商品类型", notes="批量删除商品类型")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = kindServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }

}