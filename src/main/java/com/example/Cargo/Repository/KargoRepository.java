package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Kargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface KargoRepository extends JpaRepository<Kargo, Long> {


    // Kargo numarasına göre arama metodu
    @Query("SELECT k FROM Kargo k WHERE k.kargoNo = :kargoNo")
    Optional<Kargo> findByKargoNo(String kargoNo);



    @Query("SELECT k FROM Kargo k WHERE k.gondericiAdi LIKE %:gondericiAdi%")
    // Göndericinin adına göre kargoları bulma
    List<Kargo> findByGondericiAdiContaining(String gondericiAdi);


    @Query("SELECT k FROM Kargo k WHERE k.aliciAdi LIKE %:aliciAdi%")
    // Alıcının adına göre kargoları bulma
    List<Kargo> findByAliciAdiContaining(String aliciAdi);


    @Query("SELECT k FROM Kargo k WHERE k.kargoDurumu = :kargoDurumu")
    // Kargo durumuna göre listeleme
    List<Kargo> findByKargoDurumu(String kargoDurumu);


    @Query("SELECT k FROM Kargo k WHERE k.gonderimTarihi BETWEEN :startDate AND :endDate")
    // Gönderim tarihi aralığına göre kargoları listeleme
    List<Kargo> findByGonderimTarihiBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT k FROM Kargo k WHERE k.ucret BETWEEN :minUcret AND :maxUcret")
    // Ücret aralığına göre kargoları listeleme
    List<Kargo> findByUcretBetween(BigDecimal minUcret, BigDecimal maxUcret);

    default Kargo createKargo(Kargo kargo) {
        kargo.setOlusturmaTarihi(LocalDateTime.now());
        kargo.setCreatedBy("Admin");
        return save(kargo);
    }
    default Kargo updateKargo(Long id, Kargo updatedKargo) {
        Kargo existingKargo = findById(id).orElseThrow(() -> new RuntimeException("Kargo bulunamadı."));
        existingKargo.setGondericiAdi(updatedKargo.getGondericiAdi());
        existingKargo.setGondericiSoyadi(updatedKargo.getGondericiSoyadi());
        existingKargo.setGondericiTelefon(updatedKargo.getGondericiTelefon());
        existingKargo.setAliciAdi(updatedKargo.getAliciAdi());
        existingKargo.setAliciSoyadi(updatedKargo.getAliciSoyadi());
        existingKargo.setAliciTelefon(updatedKargo.getAliciTelefon());
        existingKargo.setAliciAdres(updatedKargo.getAliciAdres());
        existingKargo.setKargoDurumu(updatedKargo.getKargoDurumu());
        existingKargo.setGonderimTarihi(updatedKargo.getGonderimTarihi());
        existingKargo.setTeslimTarihi(updatedKargo.getTeslimTarihi());
        existingKargo.setUcret(updatedKargo.getUcret());
        existingKargo.setUpdatedAt(LocalDateTime.now());
        existingKargo.setUpdatedBy("Admin");
        return save(existingKargo);
    }

    @Modifying
    @Transactional
    @Query("DELETE FROM Kargo k WHERE k.kargoId = :id")
    void deleteKargoPermanently(@Param("id") Long id);

}
