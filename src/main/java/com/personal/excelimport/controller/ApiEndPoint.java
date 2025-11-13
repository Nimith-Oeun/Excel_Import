package com.personal.excelimport.controller;

import com.personal.excelimport.service.ProductImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ApiEndPoint {

    private final ProductImportService productImportService;

    @PostMapping("/import")
    public ResponseEntity<?> importProduct(@RequestParam("file") MultipartFile file){
        Map<Integer, String> ErrorRespone = productImportService.importProduct(file);
        if(!ErrorRespone.isEmpty()){
            return ResponseEntity.ok(ErrorRespone);
        }
        return ResponseEntity.ok("Import Success");
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam("_page") int page, @RequestParam("_size") int size){
        return ResponseEntity.ok(productImportService.findAll(page, size));
    }
}
