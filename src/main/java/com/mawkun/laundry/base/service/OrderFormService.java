package com.mawkun.laundry.base.service;

import com.mawkun.laundry.base.dao.OrderFormDao;
import com.mawkun.laundry.base.entity.OrderForm;
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

    public int update(OrderForm orderForm) {
        orderForm.setUpdateTime(new Date());
        return orderFormDao.update(orderForm);
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