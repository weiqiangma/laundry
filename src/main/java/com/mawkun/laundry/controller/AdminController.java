package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.service.AdminServiceExt;
import io.swagger.annotations.ApiImplicitParam;
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
    public JsonResult getByEntity(Admin admin) {
        Admin resultAdmin = adminServiceExt.getByEntity(admin);
        return sendSuccess(resultAdmin);
    }

    @RequestMapping("/list")
    public JsonResult list(Admin admin) {
        List<Admin> adminList = adminServiceExt.listByEntity(admin);
        return sendSuccess(adminList);
    }

    @RequestMapping("/insert")
    public JsonResult insert(@RequestBody Admin admin){
        adminServiceExt.insert(admin);
        return sendSuccess(admin);
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody Admin admin){
        adminServiceExt.update(admin);
        return sendSuccess("编辑成功");
    }

    @RequestMapping("/delete/{id}")
    public JsonResult deleteOne(@PathVariable Long id){
        adminServiceExt.deleteById(id);
        return sendSuccess("删除成功");
    }

    @RequestMapping("/delete")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = adminServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }
}