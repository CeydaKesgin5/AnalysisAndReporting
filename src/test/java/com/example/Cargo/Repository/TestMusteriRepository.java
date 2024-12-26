package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Musteri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestMusteriRepository {

    @Autowired
    private MusteriRepository musteriRepository;

    private Musteri musteri;

    @BeforeEach
    void setUp() {
        musteri = new Musteri();
        musteri.setAdi("Ali");
        musteri.setSoyadi("Veli");
        musteri.setTelefon("1234567890");
        musteri.setAdres("Ankara, Turkey");
        musteri.setEmail("ali.veli@example.com");
        musteri.setCreatedAt(LocalDateTime.now());
        musteri.setCreatedBy("Admin");
        musteriRepository.save(musteri);
    }

    @Test
    void testFindByTelefon() {
        Musteri foundMusteri = musteriRepository.findByTelefon("1234567890");
        assertNotNull(foundMusteri);
        assertEquals("Ali", foundMusteri.getAdi());
    }

    @Test
    void testFindByEmail() {
        Musteri foundMusteri = musteriRepository.findByEmail("ali.veli@example.com");
        assertNotNull(foundMusteri);
        assertEquals("Ali", foundMusteri.getAdi());
    }

    @Test
    void testCreateMusteri() {
        Musteri newMusteri = new Musteri();
        newMusteri.setAdi("Ahmet");
        newMusteri.setSoyadi("Mehmet");
        newMusteri.setTelefon("0987654321");
        newMusteri.setAdres("Istanbul, Turkey");
        newMusteri.setEmail("ahmet.mehmet@example.com");

        Musteri savedMusteri = musteriRepository.createMusteri(newMusteri);
        assertNotNull(savedMusteri);
        assertEquals("Ahmet", savedMusteri.getAdi());
    }

    @Test
    void testUpdateMusteri() {
        Musteri updatedMusteri = new Musteri();
        updatedMusteri.setAdi("Ali Updated");
        updatedMusteri.setSoyadi("Veli Updated");
        updatedMusteri.setTelefon("1234567890");
        updatedMusteri.setAdres("Updated Address");
        updatedMusteri.setEmail("updated.email@example.com");

        Musteri savedMusteri = musteriRepository.updateMusteri(musteri.getId(), updatedMusteri);

        assertNotNull(savedMusteri);
        assertEquals("Ali Updated", savedMusteri.getAdi());
    }

    @Test
    void testDeleteMusteriPermanently() {
        musteriRepository.deleteMusteriPermanently(musteri.getId());
        Optional<Musteri> deletedMusteri = musteriRepository.findById(musteri.getId());
        assertTrue(deletedMusteri.isEmpty());
    }

    @Test
    void testDeleteMusteriLogical() {
        musteriRepository.deleteMusteri(musteri.getId(), "Admin");
        Musteri deletedMusteri = musteriRepository.findById(musteri.getId()).orElse(null);

        assertNotNull(deletedMusteri);
        assertNotNull(deletedMusteri.getDeletedAt());
        assertEquals("Admin", deletedMusteri.getDeletedBy());
    }

    @Test
    void testFindByAdiContaining() {
        var musteriList = musteriRepository.findByAdiContaining(musteri.getId());
        assertNotNull(musteriList);
        assertFalse(musteriList.isEmpty());
    }

    @Test
    void testFindNonExistingByTelefon() {
        Musteri foundMusteri = musteriRepository.findByTelefon("0000000000");
        assertNull(foundMusteri);
    }
}
