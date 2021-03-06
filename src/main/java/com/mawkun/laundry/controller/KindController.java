package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.data.query.KindQuery;
import com.mawkun.laundry.base.entity.Kind;
import com.mawkun.laundry.service.KindServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import com.xiaoleilu.hutool.convert.Convert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.Transformer;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
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
    public JsonResult insert(@LoginedAuth @ApiIgnore UserSession session, Kind kind, MultipartFile file){
        if(session.getShopId() > 0) return sendArgsError("子管理员无权添加商品分类");
        if(file == null || kind.getKindName() == null) return sendError("缺少参数");
        Kind resultKind = kindServiceExt.getByName(kind.getKindName());
        if(resultKind != null) return sendError("该类型已存在不能重复添加");
        kindServiceExt.insertWithPic(kind, file);
        return sendSuccess(kind);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑商品类型", notes="编辑商品类型")
    public JsonResult update(@LoginedAuth @ApiIgnore UserSession session,Kind kind, MultipartFile file){
        if(session.getShopId() > 0) return sendArgsError("子管理员无权编辑商品分类");
        int result = kindServiceExt.updateWithPic(kind, file);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除商品类型", notes="删除商品类型")
    public JsonResult deleteOne(@LoginedAuth @ApiIgnore UserSession session,Long id){
        if(session.getShopId() > 0) return sendArgsError("子管理员无权删除商品分类");
        int result = kindServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除商品类型", notes="批量删除商品类型")
    public JsonResult deleteBatch(@LoginedAuth @ApiIgnore UserSession session,String ids){
        int result = 0;
        if(session.getShopId() > 0) return sendArgsError("子管理员无权删除商品分类");
        List<String> idArray = Arrays.asList(ids.split(","));
        List idList = new ArrayList<>();
        idList = CollectionUtils.transform(idArray, new Transformer() {
            @Override
            public Object transform(Object o) {
                return Convert.toInt(o, 0);
            }
        });
        if (idList.size()>0) result = kindServiceExt.deleteByIds(idList);
        return sendSuccess(result);
    }

}