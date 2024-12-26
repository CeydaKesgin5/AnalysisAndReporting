package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Long> {
    // Müşteri adını içerenleri listeleme
    @Query("select a.Id from Musteri a WHERE a.Id= :Id")

    List<Musteri> findByAdiContaining(Long Id );


    default Musteri createMusteri(Musteri musteri) {
        musteri.setCreatedAt(LocalDateTime.now());
        musteri.setCreatedBy("Admin");
        return save(musteri);
    }

    default Musteri updateMusteri(Long id, Musteri updatedMusteri) {
        Musteri existingMusteri = findById(id).orElseThrow(() -> new RuntimeException("Müşteri bulunamadı."));
        existingMusteri.setAdi(updatedMusteri.getAdi());
        existingMusteri.setSoyadi(updatedMusteri.getSoyadi());
        existingMusteri.setTelefon(updatedMusteri.getTelefon());
        existingMusteri.setAdres(updatedMusteri.getAdres());
        existingMusteri.setEmail(updatedMusteri.getEmail());
        existingMusteri.setUpdatedAt(LocalDateTime.now());
        existingMusteri.setUpdatedBy("Admin");
        return save(existingMusteri);
    }

    default void deleteMusteri(Long id, String deletedBy) {
        Musteri existingMusteri = findById(id).orElseThrow(() -> new RuntimeException("Müşteri bulunamadı."));
        existingMusteri.setDeletedAt(LocalDateTime.now());
        existingMusteri.setDeletedBy(deletedBy);
        save(existingMusteri);
    }

    @Modifying
    @Transactional
    @Query("DELETE FROM Musteri m WHERE m.Id = :id")
    void deleteMusteriPermanently(@Param("id") Long id);

    Musteri findByEmail(String mail);

    Musteri findByTelefon(String number);
}
