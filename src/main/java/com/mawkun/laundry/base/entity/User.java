package com.mawkun.laundry.base.entity;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
                                    import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * (User)实体类
 *
 * @author mawkun
 * @date 2020-08-22 15:35:14
 */
@Data 
public class User {

        @ExcelProperty("用户ID")
        private Long id;
        @ExcelProperty("用户名")
        private String userName;
        @ExcelProperty("真实姓名")
        private String realName;
        @ExcelProperty("联系方式")
        private String mobile;
        @ExcelProperty("地址")
        private String address;
        @ExcelProperty("用户类型")
        private Integer kind;
        @ExcelProperty("余额")
        private Double sumOfMoney;
        @ExcelProperty("积分")
        private Integer integral;
        @ExcelProperty("更新时间")
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
        @ExcelProperty("创建时间")
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;

}