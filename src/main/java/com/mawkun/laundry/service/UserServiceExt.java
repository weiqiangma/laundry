package com.mawkun.laundry.service;

import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.UserQuery;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.base.service.UserService;
import com.mawkun.laundry.dao.UserDaoExt;
import com.mawkun.laundry.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceExt extends UserService {

    @Autowired
    private UserDaoExt userDaoExt;

    /**
     * 列表分页
     * @param query
     * @return
     */
    public PageInfo pageByEntity(UserQuery query) {
        query.init();
        if(!StringUtils.isEmpty(query.getUserName())) {
            query.setUserName("%" + query.getUserName() + "%");
        }
        if(!StringUtils.isEmpty(query.getMobile())) {
            query.setMobile("%" + query.getMobile() + "%");
        }
        List<User> list = userDaoExt.listByEntity(query);
        return new PageInfo(list);
    }
}