package com.example.Cargo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "arac", schema = "kargo_sistemi")
public class Arac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true, length = 20)
    private String plaka;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false)
    private double yukKapasitesi;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}

