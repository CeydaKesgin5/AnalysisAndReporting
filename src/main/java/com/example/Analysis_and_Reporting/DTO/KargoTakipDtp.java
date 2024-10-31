package com.example.Analysis_and_Reporting.DTO;

import com.example.Analysis_and_Reporting.Entity.Kargo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KargoTakipDtp {
    private Long takipId;
    private Kargo kargo;
    private String konum;
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
