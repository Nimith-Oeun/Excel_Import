package com.personal.excelimport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String size;
    private Double price;
    private String image;
    private String category;
    private String description;
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
