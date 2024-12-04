package com.example.Cargo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "kargo_takip", schema = "kargo_sistemi")
public class KargoTakip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long takipId;

    @ManyToOne
    @JoinColumn(name = "kargo_id", nullable = false)
    private Kargo kargo;

    @Column(nullable = false)
    private String konum;

    @Column(nullable = false)
    private LocalDateTime tarih;

    private String aciklama;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}
