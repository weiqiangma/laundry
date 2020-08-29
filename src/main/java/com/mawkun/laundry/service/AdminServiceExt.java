package com.mawkun.laundry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.AdminQuery;
import com.mawkun.laundry.base.entity.Admin;
import com.mawkun.laundry.base.service.AdminService;
import com.mawkun.laundry.dao.AdminDaoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceExt extends AdminService {

    @Autowired
    AdminDaoExt adminDaoExt;

    public PageInfo pageByEntity(AdminQuery query) {
        query.init();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Admin> list = adminDaoExt.listByEntity(query);
        return new PageInfo(list);
    }

}
