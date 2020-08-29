package com.mawkun.laundry.base.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * (Admin)实体类
 *
 * @author mawkun
 * @date 2020-08-22 15:25:44
 */
@Data
@ApiModel(value = "admin实体", description = "admin实体")
public class Admin {

    @ApiModelProperty(value = "管理员id", name = "id")
    private Long id;

    @ApiModelProperty(value = "店铺Id", name = "shopId")
    private Long shopId;

    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;

    @ApiModelProperty(value = "姓名", name = "realName")
    private String realName;

    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    @ApiModelProperty(value = "联系方式", name = "phone")
    private String phone;

    @ApiModelProperty(value = "等级", name = "level", example = "-1")
    private Integer level;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}