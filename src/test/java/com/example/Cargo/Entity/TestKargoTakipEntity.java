package com.example.Cargo.Entity;

import com.example.Cargo.Repository.KargoTakipRepository;
import com.example.Cargo.Repository.KargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestKargoTakipEntity {

    @Autowired
    private KargoTakipRepository kargoTakipRepository;

    @Autowired
    private KargoRepository kargoRepository;

    private Kargo kargo;
    private KargoTakip kargoTakip;

    @BeforeEach
    public void setUp() {
        // Setting up a Kargo entity
        kargo = new Kargo();
        kargo.setKargoNo("KARGO123");
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

        kargo = kargoRepository.save(kargo);

        // Setting up a KargoTakip entity
        kargoTakip = new KargoTakip();
        kargoTakip.setKargo(kargo);
        kargoTakip.setKonum("İstanbul");
        kargoTakip.setTarih(LocalDateTime.now());
        kargoTakip.setAciklama("Kargo teslim edilmek üzere yola çıktı");
        kargoTakip.setCreatedBy("admin");
        kargoTakip.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveKargoTakip() {
        KargoTakip savedKargoTakip = kargoTakipRepository.save(kargoTakip);

        assertNotNull(savedKargoTakip.getTakipId(), "Takip ID should be generated after saving");
        assertEquals("İstanbul", savedKargoTakip.getKonum(), "Konum should be the same as set");
        assertEquals(kargo.getKargoNo(), savedKargoTakip.getKargo().getKargoNo(), "Kargo No should match");
        assertNotNull(savedKargoTakip.getTarih(), "Tarih should be set");
        assertNotNull(savedKargoTakip.getCreatedAt(), "CreatedAt should be set");
    }

    @Test
    public void testFindByKargoId() {
        KargoTakip savedKargoTakip = kargoTakipRepository.save(kargoTakip);

        KargoTakip foundKargoTakip = kargoTakipRepository.findById(savedKargoTakip.getTakipId()).orElse(null);
        assertNotNull(foundKargoTakip, "KargoTakip should be found by its ID");
        assertEquals("İstanbul", foundKargoTakip.getKonum(), "Konum should match");
    }

    @Test
    public void testUpdateKargoTakip() {
        KargoTakip savedKargoTakip = kargoTakipRepository.save(kargoTakip);

        savedKargoTakip.setKonum("Ankara");
        savedKargoTakip.setUpdatedBy("admin");
        savedKargoTakip.setUpdatedAt(LocalDateTime.now());

        KargoTakip updatedKargoTakip = kargoTakipRepository.save(savedKargoTakip);

        assertEquals("Ankara", updatedKargoTakip.getKonum(), "Konum should be updated");
        assertNotNull(updatedKargoTakip.getUpdatedAt(), "UpdatedAt should be set");
        assertEquals("admin", updatedKargoTakip.getUpdatedBy(), "UpdatedBy should be set");
    }

    @Test
    public void testDeleteKargoTakip() {
        KargoTakip savedKargoTakip = kargoTakipRepository.save(kargoTakip);

        kargoTakipRepository.delete(savedKargoTakip);
        KargoTakip deletedKargoTakip = kargoTakipRepository.findById(savedKargoTakip.getTakipId()).orElse(null);

        assertNull(deletedKargoTakip, "KargoTakip should be deleted");
    }
}
