package com.example.Cargo.Entity;

import com.example.Cargo.Repository.CalisanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestCalisanEntity {

    @Autowired
    private CalisanRepository calisanRepository;

    private Calisan calisan;

    @BeforeEach
    public void setUp() {
        calisan = new Calisan();
        calisan.setAdi("Ahmet");
        calisan.setSoyadi("Yılmaz");
        calisan.setTelefon("5551234567");
        calisan.setPozisyon("Driver");
        calisan.setCreatedBy("admin");
        calisan.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveCalisan() {
        Calisan savedCalisan = calisanRepository.save(calisan);

        assertNotNull(savedCalisan.getId(), "ID should be generated after saving");
        assertEquals("Ahmet", savedCalisan.getAdi(), "Adi should be the same as set");
        assertEquals("Yılmaz", savedCalisan.getSoyadi(), "Soyadi should be the same as set");
        assertEquals("5551234567", savedCalisan.getTelefon(), "Telefon should be the same as set");
        assertEquals("Driver", savedCalisan.getPozisyon(), "Pozisyon should be the same as set");
        assertNotNull(savedCalisan.getCreatedAt(), "CreatedAt should be set");
        assertNotNull(savedCalisan.getCreatedBy(), "CreatedBy should be set");
    }

    @Test
    public void testFindByTelefon() {
        calisanRepository.save(calisan);

        Calisan foundCalisan = calisanRepository.findByTelefon("5551234567");
        assertNotNull(foundCalisan, "Calisan with telefon should be found");
        assertEquals("Ahmet", foundCalisan.getAdi(), "Adi should match");
    }

    @Test
    public void testUpdateCalisan() {
        Calisan savedCalisan = calisanRepository.save(calisan);

        savedCalisan.setPozisyon("Manager");
        savedCalisan.setUpdatedBy("admin");
        savedCalisan.setUpdatedAt(LocalDateTime.now());

        Calisan updatedCalisan = calisanRepository.save(savedCalisan);

        assertEquals("Manager", updatedCalisan.getPozisyon(), "Pozisyon should be updated");
        assertNotNull(updatedCalisan.getUpdatedAt(), "UpdatedAt should be set");
        assertEquals("admin", updatedCalisan.getUpdatedBy(), "UpdatedBy should be set");
    }

    @Test
    public void testDeleteCalisan() {
        Calisan savedCalisan = calisanRepository.save(calisan);

        calisanRepository.delete(savedCalisan);
        Calisan deletedCalisan = calisanRepository.findById(savedCalisan.getId()).orElse(null);

        assertNull(deletedCalisan, "Calisan should be deleted");
    }
}
