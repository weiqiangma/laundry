package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.dao.OperateOrderLogDao;
import com.mawkun.laundry.base.data.query.OperateOrderLogQuery;
import com.mawkun.laundry.base.entity.OperateOrderLog;

import java.util.List;

public interface OperateOrderLogDaoExt extends OperateOrderLogDao {

    List<OperateOrderLog> selectList(OperateOrderLogQuery query);
}
