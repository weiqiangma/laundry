package com.mawkun.laundry.base.data.query;

import com.mawkun.laundry.base.entity.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsQuery extends Goods {
    private Integer pageNo;
    private Integer pageSize;
    private Date createTimeStart;
    private Date createTimeEnd;

    public void init() {
        if(pageNo == null) pageNo = 1;
        if(pageSize == null) pageSize = 20;
    }
}
