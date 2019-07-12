package excel;

import annotation.DateFormation;
import bo.MyField;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.DateUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * create on 2019/7/11
 * @author jiachengyan
 */
public class FastExcel {
    private static final Integer WIDTH = 300;
    private static final Integer COLUMN_WIDTH = WIDTH * 3;

    /**
     * 传入一个对像list,完成excel的写操作
     *
     * @param dataList
     * @return
     */
    public static XSSFWorkbook createXSSFWorkbook(List dataList) {
        if (dataList == null || dataList.size() == 0) {
            return null;
        }
        /**
         * 表头
         */
        List<String> head = new ArrayList<String>();
        List<String> row;
        /**
         * 获取字段get方法，注解等属性,并且创建表头
         */
        List<MyField> myFieldList = createMyFieldAndHead(head, dataList);
        /**
         * 添加行列
         */
        List<List<String>> rowList = new ArrayList(dataList.size());
        try {
            for (Object rawData : dataList) {
                row = new ArrayList();
                for (MyField myField : myFieldList) {
                    Object column = myField.getMethod().invoke(rawData);
                    if (myField.getFormationType() != null) {
                        column = DateUtil.dateToString(column, myField.getFormationType());
                    }
                    if (column == null) {
                        row.add(null);
                    } else {
                        row.add(column.toString());
                    }
                }
                rowList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExcelData excelData = new ExcelData(head, rowList);
        return exportExcel(excelData);
    }


    /**
     * 利用反射， 获取字段get方法，注解等属性
     *
     * @param head
     * @param dataList
     * @return
     */
    private static List<MyField> createMyFieldAndHead(List<String> head, List dataList) {
        //获取对象的class类型
        Class clz = dataList.get(0).getClass();
        //获取所有字段
        Field[] fields = clz.getDeclaredFields();
        //收集需要执行的方法，以及是否有注解
        List<MyField> myFieldList = new ArrayList();
        Field field;
        try {
            for (int i = 0; i < fields.length; i++) {
                MyField myField = new MyField();
                field = fields[i];
                Method method;
                String name = field.getName();
                method = clz.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                myField.setMethod(method);
                DateFormation formation = field.getAnnotation(DateFormation.class);
                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                if (formation != null) {
                    myField.setFormationType(formation.type());
                }
                if (apiModelProperty != null) {
                    head.add(apiModelProperty.value());
                }
                myFieldList.add(myField);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myFieldList;
    }

    /**
     * 创建excel方法
     */
    private static XSSFWorkbook exportExcel(ExcelData excelData) {
        List<String> head = excelData.getHead();
        List<List<String>> body = excelData.getBody();
        int NEXT_ROW = 1;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        /** 创建表列名 */
        int columnWidth;
        XSSFRow columnNameRow = sheet.createRow(0);
        for (int i = 0; i < head.size(); i++) {
            columnWidth = head.get(i).length() * COLUMN_WIDTH;
            sheet.setColumnWidth(i, columnWidth);
            XSSFCell columnNameRowCell = columnNameRow.createCell(i);
            columnNameRowCell.setCellValue(head.get(i));
        }
        /** 列表部分记录 */
        for (int i = 0; i < body.size(); i++) {
            List<String> rowValues = body.get(i);
            XSSFRow rowCells = sheet.createRow(i + NEXT_ROW);
            for (int j = 0; j < rowValues.size(); j++) {
                XSSFCell cell = rowCells.createCell(j);
                if (rowValues.get(j) != null) {
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(rowValues.get(j));
                }
            }
        }
        return workbook;
    }
}
