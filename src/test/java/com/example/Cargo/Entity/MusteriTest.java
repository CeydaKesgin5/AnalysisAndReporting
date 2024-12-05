package com.example.Cargo.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class MusteriTest {

    @Test
    void testMusteriEntity() {
        // Parametresiz constructor ile bir Musteri nesnesi oluştur
        Musteri musteri = new Musteri();

        // Setter metotlarını kullanarak değerleri ata
        musteri.setId(1L);
        musteri.setAdi("Ahmet");
        musteri.setSoyadi("Yılmaz");
        musteri.setTelefon("1234567890");
        musteri.setAdres("İstanbul");
        musteri.setEmail("ahmet.yilmaz@example.com");
        musteri.setCreatedBy("Admin");
        musteri.setCreatedAt(LocalDateTime.of(2024, 12, 1, 12, 0));
        musteri.setUpdatedBy("Admin");
        musteri.setUpdatedAt(LocalDateTime.of(2024, 12, 2, 14, 30));
        musteri.setDeletedBy(null);
        musteri.setDeletedAt(null);
        musteri.setVersion(1);

        // Getter metotları ile değerleri kontrol et
        Assertions.assertEquals(1L, musteri.getId());
        Assertions.assertEquals("Ahmet", musteri.getAdi());
        Assertions.assertEquals("Yılmaz", musteri.getSoyadi());
        Assertions.assertEquals("1234567890", musteri.getTelefon());
        Assertions.assertEquals("İstanbul", musteri.getAdres());
        Assertions.assertEquals("ahmet.yilmaz@example.com", musteri.getEmail());
        Assertions.assertEquals("Admin", musteri.getCreatedBy());
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 1, 12, 0), musteri.getCreatedAt());
        Assertions.assertEquals("Admin", musteri.getUpdatedBy());
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 2, 14, 30), musteri.getUpdatedAt());
        Assertions.assertNull(musteri.getDeletedBy());
        Assertions.assertNull(musteri.getDeletedAt());
        Assertions.assertEquals(1, musteri.getVersion());

        // Parametreli constructor test
        Musteri parametreliMusteri = new Musteri("Ali", "Kaya", "9876543210", "Ankara");
        Assertions.assertEquals("Ali", parametreliMusteri.getAdi());
        Assertions.assertEquals("Kaya", parametreliMusteri.getSoyadi());
        Assertions.assertEquals("9876543210", parametreliMusteri.getTelefon());
        Assertions.assertEquals("Ankara", parametreliMusteri.getAdres());
    }
}
