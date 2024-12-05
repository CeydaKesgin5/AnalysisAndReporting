package com.example.Cargo.Repository;

import com.example.Cargo.Entity.KargoTakip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KargoTakipRepository extends JpaRepository<KargoTakip, Long> {
    List<KargoTakip> findByKargo_KargoId(Long kargoId);

    // Belirli bir kargo için takip bilgilerini listeleme

    // Tarih aralığında kargo takip bilgilerini getirme
    @Query("SELECT a FROM KargoTakip a WHERE  a.tarih BETWEEN :startDate AND :endDate")

    List<KargoTakip> findByTarihBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Konuma göre takip kayıtlarını bulma
    @Query("select a.konum from KargoTakip a WHERE a.konum= :konum")

    List<KargoTakip> findByKonumContaining(String konum);


    default KargoTakip createKargoTakip(KargoTakip kargoTakip) {
        kargoTakip.setCreatedAt(LocalDateTime.now());
        kargoTakip.setCreatedBy("Admin");
        return save(kargoTakip);
    }

    default KargoTakip updateKargoTakip(Long id, KargoTakip updatedKargoTakip) {
        KargoTakip existingKargoTakip = findById(id).orElseThrow(() -> new RuntimeException("Kargo Takip kaydı bulunamadı."));
        existingKargoTakip.setKonum(updatedKargoTakip.getKonum());
        existingKargoTakip.setTarih(updatedKargoTakip.getTarih());
        existingKargoTakip.setAciklama(updatedKargoTakip.getAciklama());
        existingKargoTakip.setUpdatedAt(LocalDateTime.now());
        existingKargoTakip.setUpdatedBy("Admin");
        return save(existingKargoTakip);
    }


    @Modifying
    @Transactional
    @Query("DELETE FROM KargoTakip kt WHERE kt.takipId = :id")
    void deleteKargoTakipPermanently(@Param("id") Long id);
}