package com.example.Cargo.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AracDto {

    private Long Id;
    private String plaka;
    private String model;
    private double yukKapasitesi;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}
