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
import com.xiaoleilu.hutool.convert.Convert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.Transformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:44:11
 */
@Controller
@RequestMapping("/adm/user")
@Api(tags={"用户操作接口"})
public class UserController extends BaseController {
    
    @Autowired
    private UserServiceExt userServiceExt;

    @GetMapping("/get")
    @ResponseBody
    @ApiOperation(value="用户详情", notes="用户详情")
    public JsonResult getById(Long id) {
        User user = userServiceExt.getById(id);
        return sendSuccess(user);
    }

    @ResponseBody
    @GetMapping("/getByEntity")
    @ApiOperation(value="用户详情", notes="用户详情")
    public JsonResult getByEntity(@LoginedAuth UserSession session, User user) {
        User resultUser = userServiceExt.getByEntity(user);
        return sendSuccess(resultUser);
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value="用户列表", notes="用户列表")
    public JsonResult list(User user) {
        List<User> userList = userServiceExt.listByEntity(user);
        return sendSuccess(userList);
    }

    @ResponseBody
    @GetMapping("/pageList")
    public JsonResult pageList(UserQuery userQuery) {
        PageInfo page = userServiceExt.pageByEntity(userQuery);
        return sendSuccess(page);
    }

    @ResponseBody
    @PostMapping("/insert")
    @ApiOperation(value="添加用户", notes="添加用户")
    public JsonResult insert(User user){
        int result = userServiceExt.insert(user);
        return sendSuccess(result);
    }

    @ResponseBody
    @PutMapping("/update")
    @ApiOperation(value="编辑用户", notes="编辑用户")
    public JsonResult update(@LoginedAuth @ApiIgnore UserSession session, User user){
        if(session.getShopId() > 0) return sendArgsError("子管理员无权编辑用户");
        int result = userServiceExt.update(user);
        return sendSuccess(result);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    @ApiOperation(value="删除用户", notes="删除用户")
    public JsonResult deleteOne(Long id){
        int result = userServiceExt.deleteById(id);
        return sendSuccess(result);
    }

    @ResponseBody
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value="批量删除用户", notes="批量删除用户")
    public JsonResult deleteBatch(String ids){
        int result = 0;
        List<String> idArray = Arrays.asList(ids.split(","));
        List idList = new ArrayList<>();
        idList = CollectionUtils.transform(idArray, new Transformer() {
            @Override
            public Object transform(Object o) {
                return Convert.toInt(o, 0);
            }
        });
        if (idList.size()>0) result = userServiceExt.deleteByIds(idList);
        return sendSuccess(result);
    }

    @GetMapping("/export")
    @ApiOperation(value="用户信息导出excel", notes="用户信息导出excel")
    public void export(User user, HttpServletResponse response) {
        List<User> list = userServiceExt.listByEntity(user);
        try(OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("教职工考勤统计.xlsx", "UTF-8"));
            EasyExcel.write(outputStream, User.class).sheet("用户统计").doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}