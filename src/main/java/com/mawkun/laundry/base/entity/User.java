package com.mawkun.laundry.base.entity;

import java.util.Date;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (User)实体类
 *
 * @author mawkun
 * @date 2020-09-02 23:11:44
 */
@Data
public class User {

    private Long id;

    private String userName;

    private String realName;

    private String mobile;

    private String address;

    private Integer kind;

    private Double sumOfMoney;

    private Integer integral;

    private Object status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}