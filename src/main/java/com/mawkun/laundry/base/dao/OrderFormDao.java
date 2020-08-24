package com.mawkun.laundry.base.dao;

import com.mawkun.laundry.base.entity.OrderForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 20:42:20
 */
public interface OrderFormDao {

    OrderForm getById(@NotNull Long id);

    List<OrderForm> listByEntity(OrderForm orderForm);

    OrderForm getByEntity(OrderForm orderForm);

    List<OrderForm> listByIds(@NotEmpty List<Long> list);

    int insert(@NotNull OrderForm orderForm);

    int insertBatch(@NotEmpty List<OrderForm> list);

    int update(@NotNull OrderForm orderForm);

    int updateByField(@NotNull @Param("where") OrderForm where, @NotNull @Param("set") OrderForm set);

    int updateBatch(@NotEmpty List<OrderForm> list);

    int deleteById(@NotNull Long id);

    int deleteByEntity(@NotNull OrderForm orderForm);
  
    int deleteByIds(@NotEmpty List<Long> list);
    
    int countAll();
    
    int countByEntity(OrderForm orderForm);
    
}