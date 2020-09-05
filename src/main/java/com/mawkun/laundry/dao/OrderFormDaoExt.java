package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.dao.OrderFormDao;
import com.mawkun.laundry.base.data.ShopIncomeData;
import com.mawkun.laundry.base.data.query.ShopIncomeQuery;
import com.mawkun.laundry.base.data.query.StateQuery;
import com.mawkun.laundry.base.data.vo.OrderFormVo;
import com.mawkun.laundry.base.entity.OrderForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderFormDaoExt extends OrderFormDao {
    /**
     * 统计门店收入
     * @param query
     * @return
     */
    List<ShopIncomeData> statsShopIncome(StateQuery query);

    /**
     * 门店详情
     * @param id
     * @return
     */
    OrderFormVo selectDetail(Long id);

    /**
     *
     * @return
     */
    List<OrderFormVo> selectList(StateQuery query);
    
}