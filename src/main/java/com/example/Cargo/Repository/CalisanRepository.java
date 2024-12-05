package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Arac;
import com.example.Cargo.Entity.Calisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalisanRepository extends JpaRepository<Calisan, Long> {
    // Adına göre çalışanları bulma
    @Query("select c.adi from Calisan c WHERE c.adi= :adi")

    List<Calisan> findByAdi(String adi);

    // Soyadına göre çalışanları bulma
    @Query("select c.soyadi from Calisan c WHERE c.soyadi= :soyadi")

    List<Calisan> findBySoyadi(String soyadi);

    // Pozisyona göre çalışanları listeleme
    @Query("select c.pozisyon from Calisan c WHERE c.pozisyon= :pozisyon")

    List<Calisan> findByPozisyon(String pozisyon);

    // Telefon numarasına göre çalışan bulma
    @Query("select c.telefon from Calisan c WHERE c.telefon= :telefon")

    Calisan findByTelefon(String telefon);

    @Query("select a.Id from Calisan a WHERE a.Id= :id")
    List<Arac> getCalisanById(Long id);

    default Calisan saveArac(Calisan calisan) {
        return save(calisan);
    }



    @Modifying
    @Transactional
    @Query("DELETE FROM Calisan a WHERE a.Id = :id")
    void deleteCalisanPermanently(@Param("id") Long id);


}
