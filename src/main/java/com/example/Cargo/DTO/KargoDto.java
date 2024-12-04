package com.example.Cargo.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class KargoDto {
    private Long kargoId;
    private String kargoNo;
    private String gondericiAdi;
    private String gondericiSoyadi;
    private String gondericiTelefon;
    private String aliciAdi;
    private String aliciSoyadi;
    private String aliciTelefon;
    private String aliciAdres;
    private String kargoDurumu;
    private LocalDateTime gonderimTarihi;
    private LocalDateTime teslimTarihi;
    private String urunler;
    private BigDecimal toplamAgirlik;
    private BigDecimal ucret;
    private LocalDateTime olusturmaTarihi;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Integer version;
}
