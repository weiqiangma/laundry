package com.mawkun.laundry.utils.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明：cn.pertech.help.excel<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2019/9/27 15:29<br>
 * 系统版本：1.0.0<br>
 */
@Data
public class ExcelReadTotalResult {
    private static final long serialVersionUID = 1L;
    //sheet的个数
    private int sheetCount;
    //每个sheet的数据
    private List<ExcelReadSheetResult> datas = new ArrayList<>();

}
