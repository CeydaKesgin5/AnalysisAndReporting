package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Arac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AracRepository extends JpaRepository<Arac, Long> {
    // Plakaya göre araç bulma
    Arac findByPlaka(String plaka);

    // Kapasiteye göre araçları listeleme
    List<Arac> findByYukKapasitesiGreaterThan(double kapasite);

    // Model adına göre araçları listeleme
    List<Arac> findByModelContaining(String model);

    // Aktif olmayan (silinmiş) araçları bulma
    List<Arac> findByDeletedAtNotNull();
}

