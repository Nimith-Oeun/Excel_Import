package com.personal.excelimport.service;


import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductImportService {
    Map<Integer, String> importProduct(MultipartFile file);
}
