package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Kargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class KargoRepositoryTest {

    @Autowired
    private KargoRepository kargoRepository;

    @BeforeEach
    void setUp() {
        Kargo kargo1 = new Kargo();
        kargo1.setKargoNo("12345");
        kargo1.setGondericiAdi("Ali");
        kargo1.setAliciAdi("Veli");
        kargo1.setKargoDurumu("Hazirlaniyor");
        kargo1.setGonderimTarihi(LocalDateTime.now().minusDays(1));
        kargo1.setUcret(BigDecimal.valueOf(50.0));

        Kargo kargo2 = new Kargo();
        kargo2.setKargoNo("67890");
        kargo2.setGondericiAdi("Ayse");
        kargo2.setAliciAdi("Fatma");
        kargo2.setKargoDurumu("Teslim Edildi");
        kargo2.setGonderimTarihi(LocalDateTime.now().minusDays(5));
        kargo2.setUcret(BigDecimal.valueOf(100.0));

        kargoRepository.save(kargo1);
        kargoRepository.save(kargo2);
    }

    @Test
    void testFindByKargoNo() {
        Optional<Kargo> kargo = kargoRepository.findByKargoNo("12345");
        assertTrue(kargo.isPresent());
        assertEquals("Ali", kargo.get().getGondericiAdi());
    }

    @Test
    void testFindByGondericiAdiContaining() {
        List<Kargo> kargolar = kargoRepository.findByGondericiAdiContaining("Ali");
        assertFalse(kargolar.isEmpty());
        assertEquals(1, kargolar.size());
    }

    @Test
    void testFindByAliciAdiContaining() {
        List<Kargo> kargolar = kargoRepository.findByAliciAdiContaining("Veli");
        assertFalse(kargolar.isEmpty());
        assertEquals(1, kargolar.size());
    }

    @Test
    void testFindByKargoDurumu() {
        List<Kargo> kargolar = kargoRepository.findByKargoDurumu("Hazirlaniyor");
        assertFalse(kargolar.isEmpty());
        assertEquals(1, kargolar.size());
    }

    @Test
    void testFindByGonderimTarihiBetween() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        List<Kargo> kargolar = kargoRepository.findByGonderimTarihiBetween(startDate, endDate);
        assertEquals(2, kargolar.size());
    }

    @Test
    void testFindByUcretBetween() {
        List<Kargo> kargolar = kargoRepository.findByUcretBetween(BigDecimal.valueOf(30.0), BigDecimal.valueOf(60.0));
        assertEquals(1, kargolar.size());
    }

    @Test
    @Transactional
    void testDeleteKargoPermanently() {
        kargoRepository.deleteKargoPermanently(1L);
        Optional<Kargo> kargo = kargoRepository.findById(1L);
        assertTrue(kargo.isEmpty());
    }
}
