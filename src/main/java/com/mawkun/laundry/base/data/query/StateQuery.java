package com.mawkun.laundry.base.data.query;

import lombok.Data;

import java.util.Date;

@Data
public class StateQuery {
    private Long shopId;
    private Date startTime;
    private Date endTime;
    private String formatCode;
    private Integer type;
    private int dateCount;
}
