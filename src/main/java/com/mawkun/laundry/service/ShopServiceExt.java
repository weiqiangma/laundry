package com.mawkun.laundry.service;

import cn.pertech.common.utils.RandomUtils;
import com.mawkun.laundry.base.dao.ShopDao;
import com.mawkun.laundry.base.entity.Shop;
import com.mawkun.laundry.base.service.ShopService;
import com.mawkun.laundry.utils.ImageUtils;
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
    ShopDao shopDao;

    public int insertWithPic(Shop shop, MultipartFile[] file) {
        String image = ImageUtils.uploadImages(file);
        shop.setPicture(image);
        shop.setCreateTime(new Date());
        shop.setUpdateTime(new Date());
        return shopDao.insert(shop);
    }
}