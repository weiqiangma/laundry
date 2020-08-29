package com.mawkun.laundry.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.OrderFormQuery;
import com.mawkun.laundry.base.data.vo.GoodsVo;
import com.mawkun.laundry.base.data.vo.OrderFormVo;
import com.mawkun.laundry.base.entity.OrderForm;
import com.mawkun.laundry.base.service.OrderFormService;
import com.mawkun.laundry.dao.GoodsDaoExt;
import com.mawkun.laundry.dao.OrderFormDaoExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFormServiceExt extends OrderFormService {

    @Autowired
    private OrderFormDaoExt orderFormDaoExt;
    @Autowired
    private GoodsDaoExt goodsDaoExt;

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

    /**
     * 订单详情
     * @param id
     * @return
     */
    public OrderFormVo getDetail(Long id) {
        OrderFormVo vo = orderFormDaoExt.selectDetail(id);
        List<GoodsVo> goodsList = goodsDaoExt.selectByOrderFormId(id);
        vo.setList(goodsList);
        return vo;
    }

}