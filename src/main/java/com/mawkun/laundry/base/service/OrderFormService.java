package com.mawkun.laundry.base.service;

import cn.pertech.common.spring.JsonResult;
import cn.pertech.common.utils.NumberUtils;
import cn.pertech.common.utils.StringUtils;
import com.mawkun.laundry.base.common.constant.Constant;
import com.mawkun.laundry.base.dao.OperateOrderLogDao;
import com.mawkun.laundry.base.dao.OrderFormDao;
import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.entity.OperateOrderLog;
import com.mawkun.laundry.base.entity.OrderForm;
import jodd.typeconverter.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-22 15:34:12
 */
@Service
public class OrderFormService {

    @Resource(type = OrderFormDao.class)
    private OrderFormDao orderFormDao;
    @Autowired
    private OperateOrderLogDao operateOrderLogDao;

    public OrderFormDao getOrderFormDao() {
        return orderFormDao;
    }

    public OrderForm getById(Long id) {
        return orderFormDao.getById(id);
    }

    public OrderForm getByEntity(OrderForm orderForm) {
        return orderFormDao.getByEntity(orderForm);
    }

    public List<OrderForm> listByEntity(OrderForm orderForm) {
        if(StringUtils.isEmpty(orderForm.getOrderSerial())) {
            orderForm.setOrderSerial("%" + orderForm.getOrderSerial() + "%");
        }
        return orderFormDao.listByEntity(orderForm);
    }

    public List<OrderForm> listByIds(List<Long> ids) {
        return orderFormDao.listByIds(ids);
    }

    public int insert(OrderForm orderForm) {
        Date date = new Date();
        orderForm.setCreateTime(date);
        orderForm.setUpdateTime(date);
        return orderFormDao.insert(orderForm);
    }

    public int insertBatch(List<OrderForm> list) {
        return orderFormDao.insertBatch(list);
    }

    public JsonResult update(UserSession session, OrderForm orderForm) {
        /**
         * 1.判断status不为空
         * 2.判断前端status==数据库status+1(保证流程按顺序执行)
         */
        Integer status = orderForm.getStatus();
        if(status != null) {
            OrderForm dbForm = orderFormDao.getById(orderForm.getId());
            if(!NumberUtils.equals(dbForm.getStatus() + 1, status)){
                return new JsonResult().success("请按顺序执行订单流程");
            }
            String operate = "";
            OperateOrderLog log = new OperateOrderLog();
            if(status == 2) {
                operate = "待收货";
            } else if(status == 3) {
                operate = "确认收货";
            } else if(status == 4) {
                operate = "洗涤中";
            } else if(status == 5) {
                operate = "待取货";
            } else if(status == 6) {
                operate = "已完成";
            } else {
                return new JsonResult().success("该订单已完成");
            }
            log.setUserId(session.getId());
            log.setOrderFormId(orderForm.getId());
            if(session.isAdmin()) log.setUserKind(Constant.USER_TYPE_ADMIN);
            if(session.isDistributor()) log.setUserKind(Constant.USER_TYPE_DISTRIBUTOR);
            if(session.isCustomer()) log.setUserKind(Constant.USER_TYPE_CUSTOMER);
            log.setOperate(operate);
            log.setStatus(status);
            log.setDescription("确定操作无误");
            log.setCreateTime(new Date());
            operateOrderLogDao.insert(log);
        }
        orderForm.setUpdateTime(new Date());
        int result = orderFormDao.update(orderForm);
        return new JsonResult().success(Convert.toString(result));
    }

    public int updateBatch(List<OrderForm> list) {
        return orderFormDao.updateBatch(list);
    }

    public int deleteById(Long id) {
        return orderFormDao.deleteById(id);
    }

    public int deleteByEntity(OrderForm orderForm) {
        return orderFormDao.deleteByEntity(orderForm);
    }
  
    public int deleteByIds(List<Long> list) {
        return orderFormDao.deleteByIds(list);
    }

    public int countAll() {
        return orderFormDao.countAll();
    }
    
    public int countByEntity(OrderForm orderForm) {
        return orderFormDao.countByEntity(orderForm);
    }

}