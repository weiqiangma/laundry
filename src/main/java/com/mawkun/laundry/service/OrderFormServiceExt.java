package com.mawkun.laundry.service;

import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.OrderFormQuery;
import com.mawkun.laundry.base.entity.OrderForm;
import com.mawkun.laundry.base.service.OrderFormService;
import com.mawkun.laundry.dao.OrderFormDaoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFormServiceExt extends OrderFormService {

    @Autowired
    private OrderFormDaoExt orderFormDaoExt;

    /**
     * 列表分页
     * @param orderFormQuery
     * @return
     */
    public PageInfo pageByEntity(OrderFormQuery orderFormQuery) {
        orderFormQuery.init();
        List<OrderForm> list = orderFormDaoExt.listByEntity(orderFormQuery);
        return new PageInfo(list);
    }

}