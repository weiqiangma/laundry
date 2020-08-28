package com.mawkun.laundry.base.entity;

import java.util.Date;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (OperateOrderLog)实体类
 *
 * @author mawkun
 * @date 2020-08-28 21:15:45
 */
@Data
public class OperateOrderLog {

    private Long id;

    private Long userId;

    private Long orderFormId;

    private Object userKind;

    private String operate;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}