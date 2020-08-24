package com.mawkun.laundry.utils.excel;
import lombok.Data;

/**
 * 功能说明：cn.pertech.help.excel<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2019/9/27 15:30<br>
 * 系统版本：1.0.0<br>
 */
@Data
public class ExportRange {
    private int firstRow;
    private int firstCol;
    private int lastRow;
    private int lastCol;

    public ExportRange() {}

    public ExportRange(int firstRow, int firstCol, int lastRow, int lastCol) {
        this.firstRow = firstRow;
        this.firstCol = firstCol;
        this.lastRow = lastRow;
        this.lastCol = lastCol;
    }
    public boolean isInRange(int rowInd, int colInd) {
        return firstRow <= rowInd && rowInd <= lastRow && //containsRow
                firstCol <= colInd && colInd <= lastCol; //containsColumn
    }
    public boolean isFirstRowCol(int rowInd, int colInd) {
        return firstRow == rowInd && firstCol == colInd;
    }
}
