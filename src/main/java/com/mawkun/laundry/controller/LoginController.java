package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import cn.pertech.common.utils.CryptUtils;
import com.alibaba.fastjson.JSONObject;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.base.service.UserCacheService;
import com.mawkun.laundry.service.AdminServiceExt;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {

    @Autowired
    AdminServiceExt adminServiceExt;

    @Autowired
    UserCacheService userCacheService;

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
}
