package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.dao.GoodsDao;
import com.mawkun.laundry.base.data.vo.GoodsVo;
import com.mawkun.laundry.base.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:40:42
 */
@Mapper
public interface GoodsDaoExt extends GoodsDao {

    /**
     * 根据订单id查询商品
     * @param orderId
     * @return
     */
    List<GoodsVo> selectByOrderFormId(Long orderId);

    List<Goods> selectByName(String name);

}