package com.example.Analysis_and_Reporting.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalisanDto {
    private Long Id;
    private String adi;
    private String soyadi;
    private String telefon;
    private String pozisyon;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}
