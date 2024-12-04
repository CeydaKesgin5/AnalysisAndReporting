package com.example.Cargo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "musteri", schema = "kargo_sistemi")
public class Musteri {
    public Musteri() {
    }

    // Parametreli yapıcı
    public Musteri(String ad, String soyad, String telefon, String adres) {
        this.adi = ad;
        this.soyadi = soyad;
        this.telefon = telefon;
        this.adres = adres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 100)
    private String adi;

    @Column(nullable = false, length = 100)
    private String soyadi;

    @Column(nullable = false, length = 20)
    private String telefon;

    @Column(nullable = false)
    private String adres;

    @Column(unique = true, length = 100)
    private String email;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}

