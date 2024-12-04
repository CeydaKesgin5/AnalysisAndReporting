package com.example.Cargo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "calisan", schema = "kargo_sistemi")
public class Calisan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 100)
    private String adi;

    @Column(nullable = false, length = 100)
    private String soyadi;

    @Column(nullable = false, length = 20)
    private String telefon;

    @Column(nullable = false, length = 50)
    private String pozisyon;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}

