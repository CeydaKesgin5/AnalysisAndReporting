package com.example.Analysis_and_Reporting.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "kargo", schema = "kargo_sistemi")
public class Kargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kargoId;

    @Column(nullable = false, unique = true, length = 20)
    private String kargoNo;

    @Column(nullable = false, length = 100)
    private String gondericiAdi;

    @Column(nullable = false, length = 100)
    private String gondericiSoyadi;

    @Column(nullable = false, length = 20)
    private String gondericiTelefon;

    @Column(nullable = false, length = 100)
    private String aliciAdi;

    @Column(nullable = false, length = 100)
    private String aliciSoyadi;

    @Column(nullable = false, length = 20)
    private String aliciTelefon;

    @Column(nullable = false)
    private String aliciAdres;

    @Column(length = 50)
    private String kargoDurumu = "Hazırlanıyor";

    private LocalDateTime gonderimTarihi;
    private LocalDateTime teslimTarihi;

    @Column(columnDefinition = "jsonb")
    private String urunler;

    @Column(precision = 5, scale = 2)
    private BigDecimal toplamAgirlik;

    @Column(precision = 10, scale = 2)
    private BigDecimal ucret;

    @Column(nullable = false)
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();


    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}

