package com.example.Cargo.DTO;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class MusteriDto {

    private Long Id;
    private String adi;
    private String soyadi;
    private String telefon;
    private String adres;
    private String email;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}
