package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Long> {
    // Müşteri adını içerenleri listeleme
    List<Musteri> findByAdiContaining(String adi);

    // Müşteri soyadını içerenleri listeleme
    List<Musteri> findBySoyadiContaining(String soyadi);

    // Telefon numarasına göre müşteri bulma
    Musteri findByTelefon(String telefon);

    // E-posta adresine göre müşteri bulma
    Musteri findByEmail(String email);

    // Silinmiş (deletedAt) müşterileri listeleme
    List<Musteri> findByDeletedAtNotNull();
}
