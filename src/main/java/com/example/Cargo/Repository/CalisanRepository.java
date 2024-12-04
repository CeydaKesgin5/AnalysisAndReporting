package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Calisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalisanRepository extends JpaRepository<Calisan, Long> {
    // Adına göre çalışanları bulma
    List<Calisan> findByAdi(String adi);

    // Soyadına göre çalışanları bulma
    List<Calisan> findBySoyadi(String soyadi);

    // Pozisyona göre çalışanları listeleme
    List<Calisan> findByPozisyon(String pozisyon);

    // Telefon numarasına göre çalışan bulma
    Calisan findByTelefon(String telefon);

    // Silinmiş çalışanları listeleme
    List<Calisan> findByDeletedAtNotNull();
}
