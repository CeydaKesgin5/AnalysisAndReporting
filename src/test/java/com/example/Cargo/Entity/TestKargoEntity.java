package com.example.Cargo.Entity;

import com.example.Cargo.Repository.KargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestKargoEntity {

    @Autowired
    private KargoRepository kargoRepository;

    private Kargo kargo;

    @BeforeEach
    public void setUp() {
        kargo = new Kargo();
        kargo.setKargoNo("KARGO12345");
        kargo.setGondericiAdi("Ali");
        kargo.setGondericiSoyadi("Veli");
        kargo.setGondericiTelefon("5551234567");
        kargo.setAliciAdi("Ahmet");
        kargo.setAliciSoyadi("Yılmaz");
        kargo.setAliciTelefon("5559876543");
        kargo.setAliciAdres("İstanbul, Türkiye");
        kargo.setUrunler("[{\"urun\": \"Laptop\", \"adet\": 2}]");
        kargo.setToplamAgirlik(new BigDecimal("10.50"));
        kargo.setUcret(new BigDecimal("150.75"));
        kargo.setCreatedBy("admin");
        kargo.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveKargo() {
        Kargo savedKargo = kargoRepository.save(kargo);

        assertNotNull(savedKargo.getKargoId(), "Kargo ID should be generated after saving");
        assertEquals("KARGO12345", savedKargo.getKargoNo(), "Kargo No should be the same as set");
        assertEquals("Ali", savedKargo.getGondericiAdi(), "Gonderici Adi should be the same as set");
        assertEquals("Ahmet", savedKargo.getAliciAdi(), "Alici Adi should be the same as set");
        assertEquals(new BigDecimal("150.75"), savedKargo.getUcret(), "Ucret should match");
        assertNotNull(savedKargo.getOlusturmaTarihi(), "OlusturmaTarihi should be set");
    }

    @Test
    public void testFindByKargoNo() {
        kargoRepository.save(kargo);

        Optional<Kargo> foundKargo = kargoRepository.findByKargoNo("KARGO12345");
        assertNotNull(foundKargo, "Kargo with kargoNo should be found");
        assertEquals("Ali", foundKargo.get().getGondericiAdi(), "Gonderici Adi should match");
    }

    @Test
    public void testUpdateKargo() {
        Kargo savedKargo = kargoRepository.save(kargo);

        savedKargo.setKargoDurumu("Teslim Edildi");
        savedKargo.setUpdatedBy("admin");
        savedKargo.setUpdatedAt(LocalDateTime.now());

        Kargo updatedKargo = kargoRepository.save(savedKargo);

        assertEquals("Teslim Edildi", updatedKargo.getKargoDurumu(), "Kargo durumu should be updated");
        assertNotNull(updatedKargo.getUpdatedAt(), "UpdatedAt should be set");
        assertEquals("admin", updatedKargo.getUpdatedBy(), "UpdatedBy should be set");
    }

    @Test
    public void testDeleteKargo() {
        Kargo savedKargo = kargoRepository.save(kargo);

        kargoRepository.delete(savedKargo);
        Kargo deletedKargo = kargoRepository.findById(savedKargo.getKargoId()).orElse(null);

        assertNull(deletedKargo, "Kargo should be deleted");
    }
}
