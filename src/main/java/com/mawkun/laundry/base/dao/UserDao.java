package com.mawkun.laundry.base.dao;

import com.mawkun.laundry.base.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:42:57
 */
public interface UserDao {

    User getById(@NotNull Long id);

    List<User> listByEntity(User user);

    User getByEntity(User user);

    List<User> listByIds(@NotEmpty List<Long> list);

    int insert(@NotNull User user);

    int insertBatch(@NotEmpty List<User> list);

    int update(@NotNull User user);

    int updateByField(@NotNull @Param("where") User where, @NotNull @Param("set") User set);

    int updateBatch(@NotEmpty List<User> list);

    int deleteById(@NotNull Long id);

    int deleteByEntity(@NotNull User user);
  
    int deleteByIds(@NotEmpty List<Long> list);
    
    int countAll();
    
    int countByEntity(User user);
    
}