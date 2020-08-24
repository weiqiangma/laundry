package com.mawkun.laundry.utils.excel;

import lombok.Data;

import java.io.Serializable;
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
 * 开发时间：2019/9/27 15:27<br>
 * 系统版本：1.0.0<br>
 */
@Data
public class ExcelReadSheetResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * sheet的序号
     */
    private int sheetIndex;
    /**
     * sheet的名称
     */
    private String sheetName;
    /**
     * 总行数
     */
    private int totalRowCount;
    /**
     * 总列数
     */
    private int totalComulnCount;
    /**
     * 头的行数
     */
    private int headerRowCount;
    /**
     * 头的列数(每一行多少列)
     */
    private int headerColumnCount;
    /**
     * 头部数据
     */
    private List<String[]> headerDatas = new ArrayList<>();
    /**
     * 处理后的头部数据
     */
    private List<String> handledHeader = new ArrayList<>();
    /**
     * 中间内容的数据
     */
    private List<String[]> bodyDatas = new ArrayList<>();
}
