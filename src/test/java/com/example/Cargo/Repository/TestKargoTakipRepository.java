package com.example.Cargo.Repository;

import com.example.Cargo.Entity.KargoTakip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class KargoTakipRepositoryTest {

    @Autowired
    private KargoTakipRepository kargoTakipRepository;

    @BeforeEach
    void setUp() {
        KargoTakip takip1 = new KargoTakip();
        takip1.setKonum("Istanbul");
        takip1.setTarih(LocalDateTime.now().minusHours(5));
        takip1.setAciklama("Kargo alindi.");

        KargoTakip takip2 = new KargoTakip();
        takip2.setKonum("Ankara");
        takip2.setTarih(LocalDateTime.now().minusHours(2));
        takip2.setAciklama("Kargo dagitimda.");

        kargoTakipRepository.save(takip1);
        kargoTakipRepository.save(takip2);
    }

    @Test
    void testFindByKargo_KargoId() {
        List<KargoTakip> takipList = kargoTakipRepository.findByKargo_KargoId(1L);
        assertNotNull(takipList);
    }

    @Test
    void testFindByTarihBetween() {
        LocalDateTime startDate = LocalDateTime.now().minusHours(6);
        LocalDateTime endDate = LocalDateTime.now();
        List<KargoTakip> takipList = kargoTakipRepository.findByTarihBetween(startDate, endDate);
        assertEquals(2, takipList.size());
    }

    @Test
    void testFindByKonumContaining() {
        List<KargoTakip> takipList = kargoTakipRepository.findByKonumContaining("Istanbul");
        assertFalse(takipList.isEmpty());
        assertEquals(1, takipList.size());
    }

    @Test
    @Transactional
    void testDeleteKargoTakipPermanently() {
        kargoTakipRepository.deleteKargoTakipPermanently(1L);
        Optional<KargoTakip> takip = kargoTakipRepository.findById(1L);
        assertTrue(takip.isEmpty());
    }
}
