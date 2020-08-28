package com.mawkun.laundry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.KindQuery;
import com.mawkun.laundry.base.entity.Kind;
import com.mawkun.laundry.base.service.KindService;
import com.mawkun.laundry.dao.KindDaoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindServiceExt extends KindService {

    @Autowired
    private KindDaoExt kindDaoExt;

    /**
     * 列表分业务
     * @param query
     * @return
     */
    public PageInfo pageByEntity(KindQuery query) {
        query.init();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Kind> list = kindDaoExt.listByEntity(query);
        return new PageInfo(list);
    }


}