package com.personal.excelimport.service;

import com.personal.excelimport.model.MenuItem;
import com.personal.excelimport.repository.ProductImportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.personal.excelimport.helper.ExcelReadHelper.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductImportServiceImpl implements ProductImportService{

    private final ProductImportRepository productImportRepository;

    @Override
    public Map<Integer, String> importProduct(MultipartFile file) {

        Map<Integer , String > map = new HashMap<>();

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream()); // open file location

            Sheet productSheet = workbook.getSheetAt(0); // get sheet in excel file

            Iterator<Row> productRowIterator = productSheet.iterator(); // get all row

            productRowIterator.next(); // skip header

            while (productRowIterator.hasNext()) { // loop all row

                Integer rowNumber = 0;

                try{

                    // read and collect value of cell
                    Row row = productRowIterator.next(); // skip 1 row
                    Integer cellIndex = 0;
/**
 * this impl in local without helper class it not recomment cuz the code hard to maintain

                    Cell cellNo = row.getCell(cellIndex++);
                    if (cellNo == null) continue;

                    if (cellNo.getCellType() == CellType.NUMERIC) {
                        rowNumber = (int) cellNo.getNumericCellValue();
                    } else if (cellNo.getCellType() == CellType.STRING) {
                        try {
                            rowNumber = Integer.parseInt(cellNo.getStringCellValue());
                        } catch (NumberFormatException e) {
                            rowNumber = 0;
                        }
                    }

                    Cell cellCategory = row.getCell(cellIndex++);// get cell 1
                    String category = cellCategory.getStringCellValue();

                    Cell cellDescription = row.getCell(cellIndex++);// get cell 2
                    String description = cellDescription.getStringCellValue();

                    Cell cellImage = row.getCell(cellIndex++);// get cell 3
                    String image = cellImage.getStringCellValue();

                    Cell cellIsActive = row.getCell(cellIndex++); // get cell 4
                    boolean isActive = false;
                    if (cellIsActive != null) {
                        if (cellIsActive.getCellType() == CellType.BOOLEAN) {
                            isActive = cellIsActive.getBooleanCellValue();
                        } else if (cellIsActive.getCellType() == CellType.STRING) {
                            String value = cellIsActive.getStringCellValue().trim();
                            isActive = value.equalsIgnoreCase("true");
                        }
                    }

                    Cell cellName = row.getCell(cellIndex++);// get cell 5
                    String name = cellName.getStringCellValue();

                    Cell cellPrice = row.getCell(cellIndex++); // get cell 6
                    double price = 0.0;
                    if (cellPrice != null) {
                        if (cellPrice.getCellType() == CellType.NUMERIC) {
                            price = cellPrice.getNumericCellValue();
                        } else if (cellPrice.getCellType() == CellType.STRING) {
                            String value = cellPrice.getStringCellValue().trim();
                            if (!value.isEmpty() && !value.equalsIgnoreCase("null")) {
                                try {
                                    price = Double.parseDouble(value);
                                } catch (NumberFormatException e) {
                                    price = 0.0; // or you can choose to skip / log it
                                }
                            }
                        }
                    }

                    Cell cellSize = row.getCell(cellIndex++);// get cell 7
                    String size = cellSize.getStringCellValue();
 */

                    int no = (int) getNumericCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 0

                    String category = getStringCellValue(
                            row.getCell(cellIndex++)
                    );//get cel 1

                    String description = getStringCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 2

                    String image = getStringCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 3

                    boolean isActive = getBooleanCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 4

                    String name = getStringCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 5

                    double price = getNumericCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 6

                    String size = getStringCellValue(
                            row.getCell(cellIndex++)
                    ); //get cel 7


                    // save Item
                    MenuItem menuItem = new MenuItem();
                    menuItem.setName(name);
                    menuItem.setSize(size);
                    menuItem.setPrice(price);
                    menuItem.setImage(image);
                    menuItem.setCategory(category);
                    menuItem.setDescription(description);
                    menuItem.setIsActive(isActive);
                    menuItem.setCreatedAt(LocalDateTime.now());
                    menuItem.setUpdatedAt(LocalDateTime.now());

                    productImportRepository.save(menuItem);

                }catch (Exception e){
                    map.put(rowNumber, e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
