package com.example.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.cglib.beans.BeanMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtil {
    public static <T> List<T> readExcel(FileInputStream stream,Class<T> clazz) throws Exception {
        final ArrayList<T> result = new ArrayList<>();
        final XSSFWorkbook xssfSheets = new XSSFWorkbook(stream);
        final XSSFSheet sheet = xssfSheets.getSheetAt(0);
        final int numberOfRows = sheet.getPhysicalNumberOfRows();
        final XSSFRow row = sheet.getRow(0);
        final ArrayList<String> keys = new ArrayList<>();
        for (Cell cell : row) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            keys.add(cell.getStringCellValue());
        }

        for (int i = 2;i<numberOfRows;i++){
            final XSSFRow row1 = sheet.getRow(i);
            if (row1!= null){
                int j = 0;
                final HashMap<String, String> excelMap = new HashMap<>();
                for (Cell cell : row1) {
                    if (cell!= null){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        final String value = cell.getStringCellValue();
                        if (value != null&&!value.equals("")){
                            excelMap.put(keys.get(j),value);
                            j++;
                        }
                    }
                }
                final T t = clazz.newInstance();
                final BeanMap beanMap = BeanMap.create(t);
                beanMap.putAll(excelMap);
                result.add(t);
            }
        }
        return result;
    }
}
