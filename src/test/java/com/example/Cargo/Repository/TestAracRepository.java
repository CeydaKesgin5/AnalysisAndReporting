package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Arac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AracRepositoryTest {

    @Autowired
    private AracRepository aracRepository;

    @BeforeEach
    void setUp() {
        Arac arac1 = new Arac();
        arac1.setPlaka("34ABC123");
        arac1.setModel("Ford Transit");
        arac1.setYukKapasitesi(1500);

        Arac arac2 = new Arac();
        arac2.setPlaka("06DEF456");
        arac2.setModel("Mercedes Sprinter");
        arac2.setYukKapasitesi(2000);

        aracRepository.save(arac1);
        aracRepository.save(arac2);
    }

    @Test
    void testFindByPlaka() {
        Arac arac = aracRepository.findByPlaka("34ABC123");
        assertNotNull(arac);
        assertEquals("34ABC123", arac.getPlaka());
    }

    @Test
    void testFindByYukKapasitesiGreaterThan() {
        List<Arac> araclar = aracRepository.findByYukKapasitesiGreaterThan(1000);
        assertFalse(araclar.isEmpty());
        assertEquals(2, araclar.size());
    }

    @Test
    void testFindByModelContaining() {
        List<Arac> araclar = aracRepository.findByModelContaining("Transit");
        assertFalse(araclar.isEmpty());
        assertEquals(1, araclar.size());
        assertEquals("Ford Transit", araclar.get(0).getModel());
    }

    @Test
    void testSaveArac() {
        Arac arac = new Arac();
        arac.setPlaka("35GHI789");
        arac.setModel("Renault Master");
        arac.setYukKapasitesi(1800);

        Arac savedArac = aracRepository.saveArac(arac);
        assertNotNull(savedArac);
        assertEquals("35GHI789", savedArac.getPlaka());
    }

    @Test
    void testUpdateArac() {
        Arac updatedArac = new Arac();
        updatedArac.setPlaka("34XYZ123");
        updatedArac.setModel("Ford Custom");
        updatedArac.setYukKapasitesi(1600);

        Arac updated = aracRepository.updateArac(1L, updatedArac);
        assertNotNull(updated);
        assertEquals("34XYZ123", updated.getPlaka());
    }

    @Test
    void testDeleteArac() {
        aracRepository.deleteArac(1L, "admin");
        Optional<Arac> arac = aracRepository.findById(1L);
        assertTrue(arac.isPresent());
        assertNotNull(arac.get().getDeletedAt());
    }

    @Test
    void testDeleteAracPermanently() {
        aracRepository.deleteAracPermanently(1L);
        Optional<Arac> arac = aracRepository.findById(1L);
        assertTrue(arac.isEmpty());
    }
}
