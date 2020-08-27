package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import cn.pertech.common.utils.CryptUtils;
import cn.pertech.common.utils.RandomUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.base.service.UserCacheService;
import com.mawkun.laundry.service.AdminServiceExt;
import com.mawkun.laundry.service.UserServiceExt;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@Api(value="登录controller",tags={"登录操作接口"})
public class LoginController extends BaseController {

    @Autowired
    AdminServiceExt adminServiceExt;

    @Autowired
    UserCacheService userCacheService;

    @Autowired
    UserServiceExt userServiceExt;

    @ResponseBody
    @RequestMapping(value = "/login")
    public JsonResult login(String userName, String password) {
        if (userName == null || password == null) return sendArgsError();
        password = CryptUtils.md5Safe(password);
        Admin queryAdmin = new Admin();
        queryAdmin.setUserName(userName);
        queryAdmin.setPassword(password);
        Admin admin = adminServiceExt.getByEntity(queryAdmin);
        if (admin == null){
            super.addAdminLog("管理员登录:帐号密码错误");
            return sendError("帐号密码错误");
        }
        String token = CryptUtils.md5(String.valueOf(System.currentTimeMillis()) + admin.getId() + admin.getUserName() + System.nanoTime());
        JSONObject item = new JSONObject();
        item.put("id", admin.getId());
        item.put("mobile", admin.getPhone());
        item.put("realName", admin.getRealName());
        item.put("token", token);
        UserSession session = new UserSession(token, admin);
        userCacheService.putUserSession(token, session);
        return sendSuccess(item);
    }

    @RequestMapping("/export")
    public JsonResult export(User user, HttpServletResponse response) {
        List<User> list = userServiceExt.listByEntity(user);
        try(OutputStream outputStream = response.getOutputStream()) {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户统计.xlsx", "UTF-8"));
            EasyExcel.write(outputStream, User.class).sheet("用户统计").doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
