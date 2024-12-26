package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Calisan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CalisanRepositoryTest {

    @Autowired
    private CalisanRepository calisanRepository;

    @BeforeEach
    void setUp() {
        Calisan calisan1 = new Calisan();
        calisan1.setAdi("Ali");
        calisan1.setSoyadi("Yilmaz");
        calisan1.setPozisyon("Sofor");
        calisan1.setTelefon("1234567890");

        Calisan calisan2 = new Calisan();
        calisan2.setAdi("Ayse");
        calisan2.setSoyadi("Kaya");
        calisan2.setPozisyon("Mudur");
        calisan2.setTelefon("0987654321");

        calisanRepository.save(calisan1);
        calisanRepository.save(calisan2);
    }

    @Test
    void testFindByAdi() {
        List<Calisan> calisanlar = calisanRepository.findByAdi("Ali");
        assertFalse(calisanlar.isEmpty());
        assertEquals(1, calisanlar.size());
        assertEquals("Ali", calisanlar.get(0).getAdi());
    }

    @Test
    void testFindBySoyadi() {
        List<Calisan> calisanlar = calisanRepository.findBySoyadi("Yilmaz");
        assertFalse(calisanlar.isEmpty());
        assertEquals(1, calisanlar.size());
        assertEquals("Yilmaz", calisanlar.get(0).getSoyadi());
    }

    @Test
    void testFindByPozisyon() {
        List<Calisan> calisanlar = calisanRepository.findByPozisyon("Sofor");
        assertFalse(calisanlar.isEmpty());
        assertEquals(1, calisanlar.size());
        assertEquals("Sofor", calisanlar.get(0).getPozisyon());
    }

    @Test
    void testFindByTelefon() {
        Calisan calisan = calisanRepository.findByTelefon("1234567890");
        assertNotNull(calisan);
        assertEquals("1234567890", calisan.getTelefon());
    }

    @Test
    void testDeleteCalisanPermanently() {
        calisanRepository.deleteCalisanPermanently(1L);
        Optional<Calisan> calisan = calisanRepository.findById(1L);
        assertTrue(calisan.isEmpty());
    }
}
