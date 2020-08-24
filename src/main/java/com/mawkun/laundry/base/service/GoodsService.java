package com.mawkun.laundry.base.service;

import com.mawkun.laundry.base.dao.GoodsDao;
import com.mawkun.laundry.base.entity.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-22 15:27:40
 */
@Service
public class GoodsService {

    @Resource(type = GoodsDao.class)
    private GoodsDao goodsDao;

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public Goods getById(Long id) {
        return goodsDao.getById(id);
    }

    public Goods getByEntity(Goods goods) {
        return goodsDao.getByEntity(goods);
    }

    public List<Goods> listByEntity(Goods goods) {
        return goodsDao.listByEntity(goods);
    }

    public List<Goods> listByIds(List<Long> ids) {
        return goodsDao.listByIds(ids);
    }

    public int insert(Goods goods) {
        Date date = new Date();
        goods.setCreateTime(date);
        goods.setUpdateTime(date);
        return goodsDao.insert(goods);
    }

    public int insertBatch(List<Goods> list) {
        return goodsDao.insertBatch(list);
    }

    public int update(Goods goods) {
        goods.setUpdateTime(new Date());
        return goodsDao.update(goods);
    }

    public int updateBatch(List<Goods> list) {
        return goodsDao.updateBatch(list);
    }

    public int deleteById(Long id) {
        return goodsDao.deleteById(id);
    }

    public int deleteByEntity(Goods goods) {
        return goodsDao.deleteByEntity(goods);
    }
  
    public int deleteByIds(List<Long> list) {
        return goodsDao.deleteByIds(list);
    }

    public int countAll() {
        return goodsDao.countAll();
    }
    
    public int countByEntity(Goods goods) {
        return goodsDao.countByEntity(goods);
    }

}