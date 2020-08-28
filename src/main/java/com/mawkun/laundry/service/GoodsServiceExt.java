package com.mawkun.laundry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.common.result.JsonResult;
import com.mawkun.laundry.base.data.query.GoodsQuery;
import com.mawkun.laundry.base.data.query.KindQuery;
import com.mawkun.laundry.base.entity.Goods;
import com.mawkun.laundry.base.service.GoodsService;
import com.mawkun.laundry.dao.GoodsDaoExt;
import com.sun.xml.internal.ws.api.server.LazyMOMProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Goods> list = goodsDaoExt.listByEntity(query);
        return new PageInfo(list);
    }


}