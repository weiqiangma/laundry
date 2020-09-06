package com.mawkun.laundry.base.data.query;

import com.mawkun.laundry.base.entity.OperateOrderLog;
import lombok.Data;

@Data
public class OperateOrderLogQuery extends OperateOrderLog {
    private Long shopId;
    private Integer pageNo;
    private Integer pageSize;

    public void init() {
        if(pageNo == null) pageNo = 1;
        if(pageSize == null) pageSize = 20;
    }
}
