package com.personal.excelimport.repository;

import com.personal.excelimport.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImportRepository extends JpaRepository<MenuItem, Long> {
}
