package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import cn.pertech.common.spring.JsonResult;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteWorkbook;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.service.UserServiceExt;
import com.mawkun.laundry.utils.excel.ExcelUtil;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:44:11
 */
@RestController
@RequestMapping("/adm/user")
public class UserController extends BaseController {
    
    @Autowired
    private UserServiceExt userServiceExt;

    @RequestMapping("/get/{id}")
    public User getById(@PathVariable Long id) {
        User user = userServiceExt.getById(id);
        return user!=null?user:new User();
    }

    @RequestMapping("/get")
    public JsonResult getByEntity(User user) {
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
        try(OutputStream outputStream = response.getOutputStream()) {
            WriteWorkbook workbook = new WriteWorkbook();
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX,false);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet");
            writer.write(list, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}