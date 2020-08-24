package com.mawkun.laundry.base.dao;

import com.mawkun.laundry.base.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-22 15:05:42
 */
@Mapper
public interface AdminDao {

    Admin getById(@NotNull Long id);

    List<Admin> listByEntity(Admin admin);

    Admin getByEntity(Admin admin);

    List<Admin> listByIds(@NotEmpty List<Long> list);

    int insert(@NotNull Admin admin);

    int insertBatch(@NotEmpty List<Admin> list);

    int update(@NotNull Admin admin);

    int updateByField(@NotNull @Param("where") Admin where, @NotNull @Param("set") Admin set);

    int updateBatch(@NotEmpty List<Admin> list);

    int deleteById(@NotNull Long id);

    int deleteByEntity(@NotNull Admin admin);
  
    int deleteByIds(@NotEmpty List<Long> list);
    
    int countAll();
    
    int countByEntity(Admin admin);
    
}