package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.data.ShopIncomeData;
import com.mawkun.laundry.base.data.query.ShopIncomeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderFormDaoExt {
    /**
     * 统计门店收入
     * @param query
     * @return
     */
    List<ShopIncomeData> statsShopIncome(ShopIncomeQuery query);
    
}