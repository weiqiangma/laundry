package com.mawkun.laundry.base.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "goods实体", description = "goods实体")
public class Goods {

        @ApiModelProperty(value = "商品id", name = "id")
        private Long id;

        @ApiModelProperty(value = "商品名称", name = "goodsName")
        private String goodsName;

        @ApiModelProperty(value = "商品价格", name = "price")
        private Double price;

        @ApiModelProperty(value = "商品描述", name = "description")
        private String description;

        @ApiModelProperty(value = "商品图片", name = "picture")
        private String picture;

        @ApiModelProperty(value = "商品状态", name = "status")
        private Object status;
    
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
    
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;

}