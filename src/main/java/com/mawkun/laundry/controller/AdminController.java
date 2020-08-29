package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.data.query.AdminQuery;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.service.AdminServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:01:10
 */
@RestController
@RequestMapping("/adm/admin")
@Api(tags={"管理员操作接口"})
public class AdminController extends BaseController {
    
    @Autowired
    private AdminServiceExt adminServiceExt;

    @GetMapping("/get")
    @ApiOperation(value="根据id获取admin", notes="根据id获取admin")
    public JsonResult getById(Long id) {
        Admin admin = adminServiceExt.getById(id);
        return sendSuccess(admin);
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="多条件获取admin", notes="多条件获取admin")
    public JsonResult getByEntity(@LoginedAuth @ApiIgnore UserSession session, Admin admin) {
        if(session.getShopId() > 0) admin.setId(session.getId());
        Admin resultAdmin = adminServiceExt.getByEntity(admin);
        return sendSuccess(resultAdmin);
    }

    @GetMapping("/list")
    @ApiOperation(value="获取admin集合", notes="根据条件获取admin")
    public JsonResult list(@LoginedAuth @ApiIgnore UserSession session, Admin admin) {
        if(session.getShopId() > 0) admin.setId(session.getId());
        List<Admin> adminList = adminServiceExt.listByEntity(admin);
        return sendSuccess(adminList);
    }

    @GetMapping("/pageList")
    @ApiOperation(value="管理员列表分页", notes="管理员列表分页")
    public JsonResult pageList(AdminQuery query) {
        PageInfo page = adminServiceExt.pageByEntity(query);
        return sendSuccess(page);
    }

    @PostMapping("/insert")
    @ApiOperation(value="添加admin", notes="添加admin")
    public JsonResult insert(@LoginedAuth @ApiIgnore UserSession session, Admin admin){
        if(session.getShopId() > 0) return sendSuccess("子管理员无权添加管理员，请联系主管理员添加");
        adminServiceExt.insert(admin);
        return sendSuccess(admin);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑admin", notes="编辑admin")
    public JsonResult update(@LoginedAuth @ApiIgnore UserSession session, Admin admin){
        if(session.getShopId() > 0) admin.setId(session.getId());
        adminServiceExt.update(admin);
        return sendSuccess("编辑成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除admin", notes="删除admin")
    @ApiImplicitParam(name = "id", value = "管理员ID", dataType = "Long", paramType = "header")
    public JsonResult deleteOne(@LoginedAuth @ApiIgnore UserSession session, Long id){
        if(session.getShopId() > 0) return sendSuccess("子管理员无删除其他管理员权限");
        adminServiceExt.deleteById(id);
        return sendSuccess("删除成功");
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除admin", notes="批量删除admin")
    public JsonResult deleteBatch(@LoginedAuth @ApiIgnore UserSession session, @RequestBody List<Long> ids){
        int result = 0;
        if(session.getShopId() > 0) return sendSuccess("子管理员无删除其他管理员权限");
        if (ids!=null&&ids.size()>0) result = adminServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }
}