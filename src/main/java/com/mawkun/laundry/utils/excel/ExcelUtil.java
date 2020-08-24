package com.mawkun.laundry.utils.excel;

import jxl.*;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：cn.pertech.help.excel<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2019/9/27 15:31<br>
 * 系统版本：1.0.0<br>
 */
public class ExcelUtil {
    private static final Log log = LogFactory.getLog(ExcelUtil.class);
    public static int headFontSize = 11;

    public static void exportExcel(WritableSheet sheet,List<String[]> list, List<String> headers, int[] shouldMergeCol) throws Exception {
        try {
            // 创建工作薄
            //WritableWorkbook workbook = Workbook.createWorkbook(os);
            // 创建新的一页
            //WritableSheet sheet = workbook.createSheet(sheetLable, 0);
            //sheet.getSettings().setDefaultRowHeight(440);
            int row = 0;// 当前行索引
            Map<Integer, Integer> hasSetColView = new HashMap<>();
            WritableFont defaultFont = new WritableFont(WritableFont.createFont("微软雅黑"), headFontSize, WritableFont.NO_BOLD);
            WritableCellFormat defaultFormat = new WritableCellFormat(NumberFormats.TEXT);
            defaultFormat.setFont(defaultFont);
            defaultFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 设置列宽度(列宽px值/10)
            CellView cv = new CellView(); //定义一个列显示样式
            cv.setFormat(defaultFormat);//把定义的单元格格式初始化进去
            cv.setSize(18 * 265);//设置列宽度（不设置的话是0，不会显示）
            // 写入标题
            for (int i = 0; i < headers.size(); i++) {
                String item = headers.get(i);
                WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"), headFontSize, WritableFont.BOLD);
                WritableCellFormat format = new WritableCellFormat(NumberFormats.TEXT);
                format.setFont(font);
                //format.setWrap(true);//自动换行
                format.setAlignment(Alignment.CENTRE);
                format.setVerticalAlignment(VerticalAlignment.CENTRE);
                if (i % 2 == 0) {
                    //format.setBackground(getNearestColour("#92D050"));
                } else {
                   //format.setBackground(getNearestColour("#FFC000"));
                }
                //format.setBackground(getNearestColour("#CCCCCC"));
                sheet.addCell(new Label(i, row, item, format));
                if (hasSetColView.get(i) == null) {
                    hasSetColView.put(i, 1);
                    sheet.setColumnView(i, cv);//设置工作表中第n列的样式
                }
            }
            sheet.setRowView(row, 440, false);
            row++;
            Map<Integer, String> shouldMergeColMap = new HashMap<>();//存储哪些列需要合并
            for (int i : shouldMergeCol) {
                shouldMergeColMap.put(i, "1");
            }
            List<ExportRange> exportRangeList = calExportRangeList(row, shouldMergeColMap, list);
            for (ExportRange exportRange : exportRangeList) {
                sheet.mergeCells(exportRange.getFirstCol(), exportRange.getFirstRow(), exportRange.getLastCol(), exportRange.getLastRow());
            }
            // 写入数据行
            for (; row < list.size() + 1; row++) {
                sheet.setRowView(row, 440, false);
                // 获取当前行数据
                String[] values = list.get(row - 1);
                for (int i = 0; i < values.length; i++) {
                    if (hasSetColView.get(i) == null) {
                        hasSetColView.put(i, 1);
                        sheet.setColumnView(i, cv);//设置工作表中第n列的样式
                    }
                    //format.setWrap(true);//自动换行
                    boolean rangeHandled = false;
                    if (shouldMergeColMap.get(i) != null) {
                        for (ExportRange exportRange : exportRangeList) {
                            if (exportRange.isFirstRowCol(row, i)) {
                                sheet.addCell(new Label(i, row, values[i], defaultFormat));
                                rangeHandled = true;
                                break;
                            }
                            if (exportRange.isInRange(row, i)) {
                                rangeHandled = true;
                                break;
                            }
                        }
                    }
                    if (!rangeHandled) {
                        sheet.addCell(new Label(i, row, values[i], defaultFormat));
                    }
                }
            }
            // 把创建的内容写入到输出流中，并关闭输出流
            //workbook.write();
            //workbook.close();
        } catch (Exception e) {
            log.error("导出Excel出错:", e);
            throw e;
        }
    }

    private static List<ExportRange> calExportRangeList(int firstRowIndex, Map<Integer, String> shouldMergeColMap, List<String[]> datas) {
        List<ExportRange> exportRangeList = new ArrayList<>();
        if (shouldMergeColMap != null && !shouldMergeColMap.isEmpty()) {
            Map<Integer, List<Integer>> hasValueMap = new HashMap<>();
            int endRowIndex = 0;
            if (datas != null && !datas.isEmpty()) {
                endRowIndex = datas.size() - 1 + firstRowIndex;
                for (int i = 0; i < datas.size(); i++) {
                    String[] row = datas.get(i);
                    for (int j = 0; j < row.length; j++) {
                        if (shouldMergeColMap.get(j) != null) {
                            List<Integer> values = hasValueMap.get(j);
                            if (values == null) {
                                values = new ArrayList<>();
                            }
                            if (i == 0) {
                                //第一列
                                values.add(i + firstRowIndex);
                            } else {
                                String[] lastRow = datas.get(i - 1);
                                if (!StringUtils.equals(row[j], lastRow[j])) {
                                    values.add(i + firstRowIndex);
                                }
                            }
                            hasValueMap.put(j, values);
                        }

                    }

                }
            }
            for (Map.Entry<Integer, List<Integer>> entry : hasValueMap.entrySet()) {
                int col = entry.getKey();
                List<Integer> rowList = entry.getValue();
                for (int i = 0; i < rowList.size(); i++) {
                    int thisRow = rowList.get(i);
                    if ((i == rowList.size() - 1) && thisRow != endRowIndex) {
                        //如果到了最后一个
                        ExportRange exportRange = new ExportRange(thisRow, col, endRowIndex, col);
                        exportRangeList.add(exportRange);
                    }
                    if (i > 0) {
                        int lastRow = rowList.get(i - 1);
                        if (thisRow - lastRow > 1) {
                            ExportRange exportRange = new ExportRange(lastRow, col, thisRow - 1, col);
                            exportRangeList.add(exportRange);
                        }
                    }
                }

            }
        }
        return exportRangeList;
    }

    public static Colour getNearestColour(String strColor) {
        Color cl = Color.decode(strColor);
        Colour color = null;
        Colour[] colors = Colour.getAllColours();
        if ((colors != null) && (colors.length > 0)) {
            Colour crtColor = null;
            int[] rgb = null;
            int diff = 0;
            int minDiff = 999;
            for (int i = 0; i < colors.length; i++) {
                crtColor = colors[i];
                rgb = new int[3];
                rgb[0] = crtColor.getDefaultRGB().getRed();
                rgb[1] = crtColor.getDefaultRGB().getGreen();
                rgb[2] = crtColor.getDefaultRGB().getBlue();
                diff = Math.abs(rgb[0] - cl.getRed()) + Math.abs(rgb[1] - cl.getGreen()) + Math.abs(rgb[2] - cl.getBlue());
                if (diff < minDiff) {
                    minDiff = diff;
                    color = crtColor;
                }
            }
        }
        if (color == null) {
            color = Colour.BLACK;
        }
        return color;
    }

    /**
     * <p>读取excel的一个sheet</p>
     * @param workbook
     * @param sheetIndex
     * @param headerRowCount 头的行数
     * @return
     * @author yekong1225
     */
    public static ExcelReadSheetResult readExcelSheet(Workbook workbook, int sheetIndex, int headerRowCount) {
        ExcelReadSheetResult result = new ExcelReadSheetResult();
        result.setHeaderRowCount(headerRowCount);
        result.setSheetIndex(sheetIndex);
        // 获取第sheetIndex张Sheet表
        Sheet readsheet = workbook.getSheet(sheetIndex);
        String sheetName = readsheet.getName();
        result.setSheetName(sheetName);
        //获取合并的单元格
        Range[] rangeCell = readsheet.getMergedCells();
        // 总列数
        int colSum = readsheet.getColumns();
        result.setTotalComulnCount(colSum);
        //log.info("总列数:" + colSum);
        // 总行数
        int rowSum = readsheet.getRows();
        result.setTotalRowCount(rowSum);
        //log.info("总行数:" + rowSum);
        result.setHeaderColumnCount(colSum);
        // 表头中文信息
        int row = 0;
        int realRow = headerRowCount;
        // 读取表头
        for (int j = 0; j < headerRowCount; j++) {
            String[] headers = new String[colSum];
            for (int i = 0; i < colSum; i++) {
                String str = null;
                str = readsheet.getCell(i, row).getContents();
                for (Range r : rangeCell) {
                    if (row >= r.getTopLeft().getRow() && row <= r.getBottomRight().getRow() && i >= r.getTopLeft().getColumn() && i <= r.getBottomRight().getColumn()) {
                        str = readsheet.getCell(r.getTopLeft().getColumn(), r.getTopLeft().getRow()).getContents();
                    }
                }
                if (StringUtils.isBlank(str)) {
                    headers[i] = "";
                } else {
                    headers[i] = str.trim();
                }
            }
            row++;
            result.getHeaderDatas().add(headers);
        }
        //处理表头
        List<String[]> headerDatas = result.getHeaderDatas();
        List<String> handledHeader = new ArrayList<String>();
        for (int i = 0; i < colSum; i++) {
            String[] tmpStrArr = new String[headerDatas.size()];
            for (int j = 0; j < headerDatas.size(); j++) {
                String[] strings = headerDatas.get(j);
                tmpStrArr[j] = strings[i];
            }
            String handleStr = StringUtils.join(tmpStrArr, "-");
            handledHeader.add(handleStr);
        }
        result.setHandledHeader(handledHeader);
        // 获取指定单元格的对象引用
        for (; row < rowSum; row++) {
            boolean isEmpty = true;//是否是空行
            String[] values = new String[colSum];
            for (int i = 0; i < colSum; i++) {
                String str = null;
                str = readsheet.getCell(i, row).getContents();
                for (Range r : rangeCell) {
                    if (row >= r.getTopLeft().getRow() && row <= r.getBottomRight().getRow() && i >= r.getTopLeft().getColumn() && i <= r.getBottomRight().getColumn()) {
                        str = readsheet.getCell(r.getTopLeft().getColumn(), r.getTopLeft().getRow()).getContents();
                    }
                }
                if (StringUtils.isBlank(str)) {
                    values[i] = "";
                } else {
                    values[i] = str.trim();
                    isEmpty = false;
                }
            }
            if (isEmpty) {
                continue;
            } else {
                realRow++;
            }
            result.getBodyDatas().add(values);
        }
        result.setTotalRowCount(realRow);
        //log.info("真实总行数:" + realRow);
        return result;
    }

    /**
     * 获取workBook
     * @param file
     * @return
     */
    public static Workbook getWorkbook(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            // 从读取流创建只读Workbook对象
            WorkbookSettings ws = new WorkbookSettings();
            ws.setCellValidationDisabled(true);
            ws.setSuppressWarnings(false);
            return Workbook.getWorkbook(fis,ws);
        } catch (Exception e) {
            log.error("读取excel异常:{}", e);
            return null;
        }
    }

    /**
     * jxl只能获取2003版的excel
     * @param is              输入流
     * @param headerRowCounts 每个sheet的头的行数
     * @return
     */
    public static ExcelReadTotalResult readExcelAll(InputStream is, int... headerRowCounts) throws Exception {
        Workbook workbook = null;
        ExcelReadTotalResult result;
        try {
            // 从读取流创建只读Workbook对象
            workbook = Workbook.getWorkbook(is);
            result = new ExcelReadTotalResult();
            int sheetCount = workbook.getNumberOfSheets();
            int readSheetCount = sheetCount;
            if (sheetCount != headerRowCounts.length) {
                if (headerRowCounts.length < sheetCount) {
                    readSheetCount = headerRowCounts.length;
                }
            }
            result.setSheetCount(readSheetCount);
            for (int i = 0; i < readSheetCount; i++) {
                result.getDatas().add(readExcelSheet(workbook, i, headerRowCounts[i]));
            }
        } catch (Exception e) {
            log.error("读取excel异常:{}", e);
            throw e;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            List<String> headers = new ArrayList<>();
            headers.add("课程名称");
            headers.add("代课老师");
            headers.add("学生姓名");
            headers.add("学生性别");
            headers.add("出生日期");
            List<String[]> list = new ArrayList<String[]>();
            String[] str1 = new String[]{"海贼王必修1", "老王1", "小王1", "男", "2017-06-20"};
            String[] str2 = new String[]{"海贼王必修1", "老王1", "小王2", "女", "2017-05-20"};
            String[] str3 = new String[]{"海贼王必修2", "老王2", "小王3", "男", "2017-04-20"};
            String[] str4 = new String[]{"海贼王必修2", "老王2", "小王4", "男", "2017-03-20"};
            String[] str5 = new String[]{"海贼王必修2", "老王2", "小王5", "女", "2017-01-20"};
            list.add(str1);
            list.add(str2);
            list.add(str3);
            list.add(str4);
            list.add(str5);
            //exportExcel(new FileOutputStream("D:\\test.xls"), list, headers, "课程信息", new int[]{0, 1});
            //ExcelReadTotalResult excelReadTotalResult = readExcelAll(new FileInputStream("D:\\test.xls"), new int[]{1});
            //System.out.println(JSON.toJSONString(excelReadTotalResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
