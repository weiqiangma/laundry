package com.mawkun.laundry.base.entity;

import java.util.Date;
import lombok.Data;
                            import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * (Kind)实体类
 *
 * @author mawkun
 * @date 2020-08-22 15:32:21
 */
@Data 
public class Kind {
    
        private Long id;
    
        private String kindName;
    
        private String iconName;
    
        private String description;
    
        private Object showStatus;
    
        private Object navStatus;
    
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}