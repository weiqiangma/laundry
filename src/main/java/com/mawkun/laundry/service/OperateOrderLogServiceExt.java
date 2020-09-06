package com.mawkun.laundry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.dao.OperateOrderLogDao;
import com.mawkun.laundry.base.data.query.OperateOrderLogQuery;
import com.mawkun.laundry.base.entity.OperateOrderLog;
import com.mawkun.laundry.base.service.OperateOrderLogService;
import com.mawkun.laundry.dao.OperateOrderLogDaoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateOrderLogServiceExt extends OperateOrderLogService {
    @Autowired
    private OperateOrderLogDaoExt operateOrderLogDaoExt;

    public PageInfo<OperateOrderLog> pageList(OperateOrderLogQuery query) {
        query.init();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<OperateOrderLog> list = operateOrderLogDaoExt.selectList(query);
        return new PageInfo<OperateOrderLog>(list);
    }
}
