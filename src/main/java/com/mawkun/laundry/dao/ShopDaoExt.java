package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.dao.ShopDao;
import com.mawkun.laundry.base.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopDaoExt extends ShopDao {

    List<Shop> selectByName(String name);

}