package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
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
    @ApiOperation(value="根据id获取商品类型", notes="根据id获取商品类型")
    public JsonResult getById(Long id) {
        Kind kind = kindServiceExt.getById(id);
        return sendSuccess(kind);
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="根据entity获取商品类型", notes="根据entity获取商品类型")
    public JsonResult getByEntity(Kind kind) {
        Kind kindResult = kindServiceExt.getByEntity(kind);
        return sendSuccess(kindResult);
    }

    @GetMapping("/list")
    @ApiOperation(value="根据entity获取商品list", notes="根据entity获取商品list")
    public JsonResult list(Kind kind) {
        List<Kind> kindList = kindServiceExt.listByEntity(kind);
        return sendSuccess(kindList);
    }

    @PostMapping("/insert")
    @ApiOperation(value="根据entity添加商品类型", notes="根据entity添加商品类型")
    public JsonResult insert(Kind kind){
        kindServiceExt.insert(kind);
        return sendSuccess(kind);
    }

    @PutMapping("/update")
    @ApiOperation(value="根据entity编辑商品类型", notes="根据entity编辑商品类型")
    public JsonResult update(Kind kind){
        int result = kindServiceExt.update(kind);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="根据id删除商品类型", notes="根据id删除商品类型")
    public JsonResult deleteOne(Long id){
        int result = kindServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = kindServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }

}