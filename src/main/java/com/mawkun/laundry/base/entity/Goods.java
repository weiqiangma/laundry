package com.mawkun.laundry.base.entity;

import java.util.Date;
import lombok.Data;
                            import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * (Goods)实体类
 *
 * @author mawkun
 * @date 2020-08-22 15:27:40
 */
@Data 
public class Goods {
    
        private Long id;
    
        private String goodsName;
    
        private Double price;
    
        private String description;
    
        private String picture;
    
        private Object status;
    
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
    
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;

}