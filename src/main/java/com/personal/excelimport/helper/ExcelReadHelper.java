package com.personal.excelimport.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelReadHelper {

    public static String getStringCellValue(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    public static double getNumericCellValue(Cell cell) {
        if (cell == null) return 0;
        try {
            return cell.getNumericCellValue();
        } catch (Exception e) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (Exception ex) {
                return 0;
            }
        }
    }

    public static boolean getBooleanCellValue(Cell cell) {
        if (cell == null) return false;
        try {
            return cell.getBooleanCellValue();
        } catch (Exception e) {
            String val = cell.getStringCellValue().trim().toLowerCase();
            return val.equals("true");
        }
    }

}
