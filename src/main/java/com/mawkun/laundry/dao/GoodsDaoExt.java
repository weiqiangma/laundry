package com.mawkun.laundry.dao;

import com.mawkun.laundry.base.dao.GoodsDao;
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

}