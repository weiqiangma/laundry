package com.mawkun.laundry.service;

import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.UserQuery;
import com.mawkun.laundry.base.entity.User;
import com.mawkun.laundry.base.service.UserService;
import com.mawkun.laundry.dao.UserDaoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceExt extends UserService {

    @Autowired
    private UserDaoExt userDaoExt;

    /**
     * 列表分页
     * @param userQuery
     * @return
     */
    public PageInfo pageByEntity(UserQuery userQuery) {
        userQuery.init();
        List<User> list = userDaoExt.listByEntity(userQuery);
        return new PageInfo(list);
    }
}