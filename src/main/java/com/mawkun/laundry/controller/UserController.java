package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import cn.pertech.common.utils.RandomUtils;
import com.alibaba.excel.EasyExcel;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.service.UserServiceExt;
import com.mawkun.laundry.spring.annotation.LoginedAuth;
import io.swagger.annotations.Api;
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
@Api(value="用户controller",tags={"用户操作接口"})
public class UserController extends BaseController {
    
    @Autowired
    private UserServiceExt userServiceExt;

    @RequestMapping("/get/{id}")
    public User getById(@PathVariable Long id) {
        User user = userServiceExt.getById(id);
        return user!=null?user:new User();
    }

    @RequestMapping("/get")
    public JsonResult getByEntity(@LoginedAuth UserSession session, User user) {
        session.getUserName();
        User resultUser = userServiceExt.getByEntity(user);
        return sendSuccess(resultUser);
    }

    @RequestMapping("/list")
    public JsonResult list(User user) {
        List<User> userList = userServiceExt.listByEntity(user);
        return sendSuccess(userList);
    }

    @RequestMapping("/insert")
    public JsonResult insert(User user){
        int result = userServiceExt.insert(user);
        return sendSuccess(result);
    }

    @RequestMapping("/update")
    public int update(@RequestBody User user){
        return userServiceExt.update(user);
    }

    @RequestMapping("/delete/{id}")
    public int deleteOne(@PathVariable Long id){
        return userServiceExt.deleteById(id);
    }

    @RequestMapping("/delete")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = userServiceExt.deleteByIds(ids);
        return result;
    }

    @RequestMapping("/export")
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