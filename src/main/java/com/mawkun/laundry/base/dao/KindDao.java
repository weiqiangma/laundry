package com.mawkun.laundry.base.dao;

import com.mawkun.laundry.base.entity.Kind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:41:36
 */
public interface KindDao {

    Kind getById(@NotNull Long id);

    List<Kind> listByEntity(Kind kind);

    Kind getByEntity(Kind kind);

    List<Kind> listByIds(@NotEmpty List<Long> list);

    int insert(@NotNull Kind kind);

    int insertBatch(@NotEmpty List<Kind> list);

    int update(@NotNull Kind kind);

    int updateByField(@NotNull @Param("where") Kind where, @NotNull @Param("set") Kind set);

    int updateBatch(@NotEmpty List<Kind> list);

    int deleteById(@NotNull Long id);

    int deleteByEntity(@NotNull Kind kind);
  
    int deleteByIds(@NotEmpty List<Long> list);
    
    int countAll();
    
    int countByEntity(Kind kind);
    
}