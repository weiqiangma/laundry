package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import cn.pertech.common.utils.RandomUtils;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.data.query.UserQuery;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.service.UserServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:44:11
 */
@RestController
@RequestMapping("/adm/user")
@Api(tags={"用户操作接口"})
public class UserController extends BaseController {
    
    @Autowired
    private UserServiceExt userServiceExt;

    @GetMapping("/get")
    @ApiOperation(value="用户详情", notes="用户详情")
    public JsonResult getById(Long id) {
        User user = userServiceExt.getById(id);
        return sendSuccess(user);
    }

    @GetMapping("/getByEntity")
    @ApiOperation(value="用户详情", notes="用户详情")
    public JsonResult getByEntity(@LoginedAuth UserSession session, User user) {
        User resultUser = userServiceExt.getByEntity(user);
        return sendSuccess(resultUser);
    }

    @GetMapping("/list")
    @ApiOperation(value="用户列表", notes="用户列表")
    public JsonResult list(User user) {
        List<User> userList = userServiceExt.listByEntity(user);
        return sendSuccess(userList);
    }

    @GetMapping("/pageList")
    public JsonResult pageList(UserQuery userQuery) {
        PageInfo page = userServiceExt.pageByEntity(userQuery);
        return sendSuccess(page);
    }

    @PostMapping("/insert")
    @ApiOperation(value="添加用户", notes="添加用户")
    public JsonResult insert(User user){
        int result = userServiceExt.insert(user);
        return sendSuccess(result);
    }

    @PutMapping("/update")
    @ApiOperation(value="编辑用户", notes="编辑用户")
    public JsonResult update(User user){
        int result = userServiceExt.update(user);
        return sendSuccess(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="删除用户", notes="删除用户")
    public JsonResult deleteOne(Long id){
        int result = userServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除用户", notes="批量删除用户")
    public JsonResult deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = userServiceExt.deleteByIds(ids);
        return sendSuccess(result);
    }

    @GetMapping("/export")
    public JsonResult export(User user, HttpServletResponse response) {
        List<User> list = userServiceExt.listByEntity(user);
        try {
            String fileName = RandomUtils.uuid() + System.currentTimeMillis();
            EasyExcel.write(fileName, User.class).sheet("用户统计").doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}