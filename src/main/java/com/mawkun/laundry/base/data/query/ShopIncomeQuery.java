package com.mawkun.laundry.base.data.query;

import lombok.Data;

import java.util.Date;

@Data
public class ShopIncomeQuery {
    private Long shopId;
    private Date startTime;
    private Date endTime;
}
