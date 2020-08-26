package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.service.AdminServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:01:10
 */
@RestController
@RequestMapping("/adm/admin")
public class AdminController extends BaseController {
    
    @Autowired
    private AdminServiceExt adminServiceExt;

    @RequestMapping("/get/{id}")
    public JsonResult getById(@PathVariable Long id) {
        Admin admin = adminServiceExt.getById(id);
        return sendSuccess(admin);
    }

    @RequestMapping("/get")
    public JsonResult getByEntity(@LoginedAuth UserSession session, Admin admin) {
        if(session.getShopId() > 0) admin.setId(session.getId());
        Admin resultAdmin = adminServiceExt.getByEntity(admin);
        return sendSuccess(resultAdmin);
    }

    @RequestMapping("/list")
    public JsonResult list(@LoginedAuth UserSession session, Admin admin) {
        if(session.getShopId() > 0) admin.setId(session.getId());
        List<Admin> adminList = adminServiceExt.listByEntity(admin);
        return sendSuccess(adminList);
    }

    @RequestMapping("/insert")
    public JsonResult insert(@LoginedAuth UserSession session, Admin admin){
        if(session.getShopId() > 0) return sendSuccess("子管理员无权添加管理员，请联系主管理员添加");
        adminServiceExt.insert(admin);
        return sendSuccess(admin);
    }

    @RequestMapping("/update")
    public JsonResult update(@LoginedAuth UserSession session, Admin admin){
        if(session.getShopId() > 0) admin.setId(session.getId());
        adminServiceExt.update(admin);
        return sendSuccess("编辑成功");
    }

    @RequestMapping("/delete")
    public JsonResult deleteOne(@LoginedAuth UserSession session, Long id){
        if(session.getShopId() > 0) return sendSuccess("子管理员无删除其他管理员权限");
        adminServiceExt.deleteById(id);
        return sendSuccess("删除成功");
    }

    @RequestMapping("/deleteBatch")
    public JsonResult deleteBatch(@LoginedAuth UserSession session, @RequestBody List<Long> ids){
        int result = 0;
        if(session.getShopId() > 0) return sendSuccess("子管理员无删除其他管理员权限");
        if (ids!=null&&ids.size()>0) result = adminServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }
}