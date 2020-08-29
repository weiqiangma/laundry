package com.mawkun.laundry.service;

import cn.pertech.common.utils.RandomUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.dao.ShopDao;
import com.mawkun.laundry.base.data.ShopIncomeData;
import com.mawkun.laundry.base.data.query.ShopIncomeQuery;
import com.mawkun.laundry.base.data.query.ShopQuery;
import com.mawkun.laundry.base.entity.Shop;
import com.mawkun.laundry.base.service.ShopService;
import com.mawkun.laundry.dao.OrderFormDaoExt;
import com.mawkun.laundry.dao.ShopDaoExt;
import com.mawkun.laundry.utils.ImageUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceExt extends ShopService {

    @Autowired
    private ShopDaoExt shopDaoExt;
    @Autowired
    private OrderFormDaoExt orderFormDaoExt;

    /**
     * 添加店铺
     * @param shop
     * @param files
     * @return
     */
    public int insertWithPic(Shop shop, MultipartFile[] files) {
        String image = ImageUtils.uploadImages(files);
        shop.setPicture(image);
        shop.setCreateTime(new Date());
        shop.setUpdateTime(new Date());
        return shopDaoExt.insert(shop);
    }

    /**
     * 编辑代纳普
     * @param shop
     * @param files
     * @return
     */
    public int updateWithPic(Shop shop, MultipartFile[] files) {
        String newImage = ImageUtils.uploadImages(files);
        if(shop.getPicture() != null) {
            StringBuilder strBuilder = new StringBuilder();
            String images = strBuilder.append(shop.getPicture()).append(",").append(newImage).toString();
            shop.setPicture(images);
        }
        return shopDaoExt.update(shop);
    }

    /**
     * 统计店铺收入
     * @param query
     * @return
     */
    public List<ShopIncomeData> statsShopIncome(ShopIncomeQuery query) {
        return orderFormDaoExt.statsShopIncome(query);
    }

    /**
     * 门店列表分业
     * @param shopQuery
     * @return
     */
    public PageInfo pageByEntity(ShopQuery shopQuery) {
        shopQuery.init();
        PageHelper.startPage(shopQuery.getPageNo(), shopQuery.getPageSize());
        List<Shop> list = shopDaoExt.listByEntity(shopQuery);
        return new PageInfo(list);
    }
}