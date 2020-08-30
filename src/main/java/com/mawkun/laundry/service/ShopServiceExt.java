package com.mawkun.laundry.service;

import cn.pertech.common.utils.DateUtils;
import cn.pertech.common.utils.RandomUtils;
import cn.pertech.common.utils.RequestUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.dao.ShopDao;
import com.mawkun.laundry.base.data.ShopIncomeData;
import com.mawkun.laundry.base.data.query.ShopIncomeQuery;
import com.mawkun.laundry.base.data.query.ShopQuery;
import com.mawkun.laundry.base.data.query.StateQuery;
import com.mawkun.laundry.base.entity.OrderForm;
import com.mawkun.laundry.base.entity.Shop;
import com.mawkun.laundry.base.service.ShopService;
import com.mawkun.laundry.dao.OrderFormDaoExt;
import com.mawkun.laundry.dao.ShopDaoExt;
import com.mawkun.laundry.utils.ImageUtils;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
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
        if(files != null && files.length > 0) {
            String images = "";
            Shop resultShop = shopDaoExt.getById(shop.getId());
            String newImage = ImageUtils.uploadImages(files);
            StringBuilder strBuilder = new StringBuilder();
            if(shop.getPicture() != null) {
                //已有的图片如果有删除会传还存在的picture
                images = strBuilder.append(shop.getPicture()).append(",").append(newImage).toString();
            } else{
                //已有的图片没动，查数据库中是否存在picture，有就在其后添加，没有新增
                if(resultShop.getPicture() != null) {
                    images =strBuilder.append(resultShop.getPicture()).append(",").append(newImage).toString();
                } else {
                    images = newImage;
                }
            }
            shop.setPicture(images);
        }
        return shopDaoExt.update(shop);
    }

    /**
     * 统计店铺收入
     * @param query
     * @return
     */
    public List<ShopIncomeData> statsShopIncome(StateQuery query) {
        List<OrderForm> list = orderFormDaoExt.selectList(query);
        Map<Long, OrderForm> map = list.stream().collect(Collectors.toMap(OrderForm::getShopId, Function.identity()));
        Date sTime = query.getStartTime();
        Date eTime = query.getEndTime();
        Calendar ca = Calendar.getInstance();
        ca.setTime(sTime);
        for(int i = 0; i < query.getDateCount(); i++) {
            String key = "";
            if(query.getType()==1){
                key = String.valueOf(i);
            }else if(query.getType()==2){
                key = DateUtils.format("yyyy-MM-dd",ca.getTime());
            }else if(query.getType()==3 || query.getType()==4){
                key = DateUtils.format("yyyy-MM-dd",ca.getTime());
            }else{
                continue;
            }
        }
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
        return new PageInfo<Shop>(list);
    }

    public List<Shop> getByName(String name) {
        return shopDaoExt.selectByName(name);
    }
}