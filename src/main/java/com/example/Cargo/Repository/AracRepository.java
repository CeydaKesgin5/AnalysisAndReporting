package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Arac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AracRepository extends JpaRepository<Arac, Long> {
    // Plakaya göre araç bulma
    @Query("select a.plaka from Arac a WHERE a.plaka= :plaka")
    Arac findByPlaka(String plaka);

    // Kapasiteye göre araçları listeleme
    @Query("select a.yukKapasitesi from Arac a WHERE a.yukKapasitesi= :kapasite")
    List<Arac> findByYukKapasitesiGreaterThan(double kapasite);

    // Model adına göre araçları listeleme
    @Query("select a.model from Arac a WHERE a.model= :model")

    List<Arac> findByModelContaining(String model);

    @Query("select a.Id from Arac a WHERE a.Id= :id")
    List<Arac> getAracById(Long id);

    default Arac saveArac(Arac arac) {
        return save(arac);
    }


    default Arac updateArac(Long id, Arac updatedArac) {
        Arac existingArac = findById(id).orElseThrow(() -> new RuntimeException("Araç bulunamadı."));
        existingArac.setPlaka(updatedArac.getPlaka());
        existingArac.setModel(updatedArac.getModel());
        existingArac.setYukKapasitesi(updatedArac.getYukKapasitesi());
        existingArac.setUpdatedAt(LocalDateTime.now());
        existingArac.setUpdatedBy(updatedArac.getUpdatedBy());
        return save(existingArac);
    }


    default void deleteArac(Long id, String deletedBy) {
        Arac arac = findById(id).orElseThrow(() -> new RuntimeException("Araç bulunamadı."));
        arac.setDeletedAt(LocalDateTime.now());
        arac.setDeletedBy(deletedBy);
        save(arac);
    }

    @Modifying
    @Transactional
    @Query("DELETE FROM Arac a WHERE a.Id = :id")
    void deleteAracPermanently(@Param("id") Long id);



}

