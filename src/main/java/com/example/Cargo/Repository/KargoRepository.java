package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Kargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface KargoRepository extends JpaRepository<Kargo, Long> {
    // Kargo numarasına göre arama metodu
    Optional<Kargo> findByKargoNo(String kargoNo);


    // Göndericinin adına göre kargoları bulma
    List<Kargo> findByGondericiAdiContaining(String gondericiAdi);

    // Alıcının adına göre kargoları bulma
    List<Kargo> findByAliciAdiContaining(String aliciAdi);

    // Kargo durumuna göre listeleme
    List<Kargo> findByKargoDurumu(String kargoDurumu);

    // Gönderim tarihi aralığına göre kargoları listeleme
    List<Kargo> findByGonderimTarihiBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Ücret aralığına göre kargoları listeleme
    List<Kargo> findByUcretBetween(BigDecimal minUcret, BigDecimal maxUcret);

    // Silinmiş kargoları bulma
    List<Kargo> findByDeletedAtNotNull();
}
