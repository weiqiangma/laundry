package com.mawkun.laundry.base.dao;

import com.mawkun.laundry.base.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:42:37
 */
public interface ShopDao {

    Shop getById(@NotNull Long id);

    List<Shop> listByEntity(Shop shop);

    Shop getByEntity(Shop shop);

    List<Shop> listByIds(@NotEmpty List<Long> list);

    int insert(@NotNull Shop shop);

    int insertBatch(@NotEmpty List<Shop> list);

    int update(@NotNull Shop shop);

    int updateByField(@NotNull @Param("where") Shop where, @NotNull @Param("set") Shop set);

    int updateBatch(@NotEmpty List<Shop> list);

    int deleteById(@NotNull Long id);

    int deleteByEntity(@NotNull Shop shop);
  
    int deleteByIds(@NotEmpty List<Long> list);
    
    int countAll();
    
    int countByEntity(Shop shop);
    
}