package com.example.Cargo.Repository;

import com.example.Cargo.Entity.KargoTakip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KargoTakipRepository extends JpaRepository<KargoTakip, Long> {
    List<KargoTakip> findByKargo_KargoId(Long kargoId);

    // Belirli bir kargo için takip bilgilerini listeleme

    // Tarih aralığında kargo takip bilgilerini getirme
    List<KargoTakip> findByTarihBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Konuma göre takip kayıtlarını bulma
    List<KargoTakip> findByKonumContaining(String konum);

    // Silinmiş takip kayıtlarını listeleme
    List<KargoTakip> findByDeletedAtNotNull();
}