package com.example.Cargo.Entity;

import com.example.Cargo.Repository.AracRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestAracEntity {

    @Autowired
    private AracRepository aracRepository;

    private Arac arac;

    @BeforeEach
    public void setUp() {
        arac = new Arac();
        arac.setPlaka("34ABC123");
        arac.setModel("Ford Transit");
        arac.setYukKapasitesi(1500.0);
        arac.setCreatedBy("admin");
        arac.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveArac() {
        Arac savedArac = aracRepository.save(arac);

        assertNotNull(savedArac.getId(), "ID should be generated after saving");
        assertEquals("34ABC123", savedArac.getPlaka(), "Plaka should be the same as set");
        assertEquals("Ford Transit", savedArac.getModel(), "Model should be the same as set");
        assertEquals(1500.0, savedArac.getYukKapasitesi(), "Yuk kapasitesi should be the same as set");
        assertNotNull(savedArac.getCreatedAt(), "CreatedAt should be set");
        assertNotNull(savedArac.getCreatedBy(), "CreatedBy should be set");
    }

    @Test
    public void testFindByPlaka() {
        aracRepository.save(arac);

        Arac foundArac = aracRepository.findByPlaka("34ABC123");
        assertNotNull(foundArac, "Arac with plaka should be found");
        assertEquals("Ford Transit", foundArac.getModel(), "Model should match");
    }

    @Test
    public void testUpdateArac() {
        Arac savedArac = aracRepository.save(arac);

        savedArac.setModel("Mercedes Sprinter");
        savedArac.setUpdatedBy("admin");
        savedArac.setUpdatedAt(LocalDateTime.now());

        Arac updatedArac = aracRepository.save(savedArac);

        assertEquals("Mercedes Sprinter", updatedArac.getModel(), "Model should be updated");
        assertNotNull(updatedArac.getUpdatedAt(), "UpdatedAt should be set");
        assertEquals("admin", updatedArac.getUpdatedBy(), "UpdatedBy should be set");
    }

    @Test
    public void testDeleteArac() {
        Arac savedArac = aracRepository.save(arac);

        aracRepository.delete(savedArac);
        Arac deletedArac = aracRepository.findById(savedArac.getId()).orElse(null);

        assertNull(deletedArac, "Arac should be deleted");
    }
}
