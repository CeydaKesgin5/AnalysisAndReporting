package com.example.Cargo.Entity;

import com.example.Cargo.Repository.MusteriRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestMusteriEntity {

    @Autowired
    private MusteriRepository musteriRepository;

    private Musteri musteri;

    @BeforeEach
    public void setUp() {
        musteri = new Musteri();
        musteri.setAdi("Ahmet");
        musteri.setSoyadi("Kaya");
        musteri.setTelefon("5551234567");
        musteri.setAdres("İstanbul, Türkiye");
        musteri.setEmail("ahmet.kaya@example.com");
        musteri.setCreatedBy("admin");
        musteri.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveMusteri() {
        Musteri savedMusteri = musteriRepository.save(musteri);

        assertNotNull(savedMusteri.getId(), "Musteri ID should be generated after saving");
        assertEquals("Ahmet", savedMusteri.getAdi(), "Adi should be the same as set");
        assertEquals("Kaya", savedMusteri.getSoyadi(), "Soyadi should be the same as set");
        assertEquals("5551234567", savedMusteri.getTelefon(), "Telefon should be the same as set");
        assertNotNull(savedMusteri.getCreatedAt(), "CreatedAt should be set");
    }

    @Test
    public void testFindByTelefon() {
        musteriRepository.save(musteri);

        Musteri foundMusteri = musteriRepository.findByTelefon("5551234567");
        assertNotNull(foundMusteri, "Musteri with telefon number should be found");
        assertEquals("Ahmet", foundMusteri.getAdi(), "Adi should match");
        assertEquals("Kaya", foundMusteri.getSoyadi(), "Soyadi should match");
    }

    @Test
    public void testFindByEmail() {
        musteriRepository.save(musteri);

        Musteri foundMusteri = musteriRepository.findByEmail("ahmet.kaya@example.com");
        assertNotNull(foundMusteri, "Musteri with email should be found");
        assertEquals("Ahmet", foundMusteri.getAdi(), "Adi should match");
    }

    @Test
    public void testUpdateMusteri() {
        Musteri savedMusteri = musteriRepository.save(musteri);

        savedMusteri.setAdi("Mehmet");
        savedMusteri.setSoyadi("Demir");
        savedMusteri.setUpdatedBy("admin");
        savedMusteri.setUpdatedAt(LocalDateTime.now());

        Musteri updatedMusteri = musteriRepository.save(savedMusteri);

        assertEquals("Mehmet", updatedMusteri.getAdi(), "Adi should be updated");
        assertEquals("Demir", updatedMusteri.getSoyadi(), "Soyadi should be updated");
        assertNotNull(updatedMusteri.getUpdatedAt(), "UpdatedAt should be set");
        assertEquals("admin", updatedMusteri.getUpdatedBy(), "UpdatedBy should be set");
    }

    @Test
    public void testDeleteMusteri() {
        Musteri savedMusteri = musteriRepository.save(musteri);

        musteriRepository.delete(savedMusteri);
        Musteri deletedMusteri = musteriRepository.findById(savedMusteri.getId()).orElse(null);

        assertNull(deletedMusteri, "Musteri should be deleted");
    }
}
