package com.mawkun.laundry.base.data.vo;

import com.mawkun.laundry.base.entity.OrderForm;
import lombok.Data;

import java.util.List;

@Data
public class OrderFormVo extends OrderForm {

    private String customerName;        //客户姓名
    private String distributorName;     //配送员姓名
    private String shopName;
    private Integer amount;
    private List<GoodsVo> list;         //订单下商品
    private String type;
}
