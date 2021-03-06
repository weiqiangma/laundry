package com.mawkun.laundry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.GoodsQuery;
import com.mawkun.laundry.base.entity.Goods;
import com.mawkun.laundry.base.service.GoodsService;
import com.mawkun.laundry.dao.GoodsDaoExt;
import com.mawkun.laundry.utils.ImageUtils;
import com.mawkun.laundry.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:40:49
 */
@Service
public class GoodsServiceExt extends GoodsService {

    @Autowired
    private GoodsDaoExt goodsDaoExt;

    public PageInfo pageByEntity(GoodsQuery query) {
        query.init();
        if(!StringUtils.isEmpty(query.getGoodsName())) {
            query.setGoodsName("%" + query.getGoodsName() + "%");
        }
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Goods> list = goodsDaoExt.listByEntity(query);
        return new PageInfo(list);
    }

    public int insertWithPic(Goods goods, MultipartFile file) {
        MultipartFile[] files = {file};
        String image = ImageUtils.uploadImages(files);
        goods.setPicture(image);
        return goodsDaoExt.insert(goods);
    }

    public int updateWithPic(Goods goods, MultipartFile file) {
        if(file != null) {
            MultipartFile[] files = {file};
            String image = ImageUtils.uploadImages(files);
            goods.setPicture(image);
        }
        return goodsDaoExt.update(goods);
    }

    public List<Goods> getByName(String name) {
        return goodsDaoExt.selectByName(name);
    }

}