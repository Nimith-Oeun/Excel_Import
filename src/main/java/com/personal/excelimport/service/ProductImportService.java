package com.personal.excelimport.service;


import com.personal.excelimport.model.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductImportService {

//    Map<Integer, String> importProduct(MultipartFile file); // This menially customize a read Excel file

    Map<Integer, String> importProduct(MultipartFile file); // using best practice for read an Excel file

    Page<MenuItem> findAll(int page, int size);
}
