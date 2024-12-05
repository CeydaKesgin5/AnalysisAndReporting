package com.example.Cargo.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class KargoTest {

    @Test
    void testKargoEntity() {
        // Parametresiz constructor ile bir Kargo nesnesi oluştur
        Kargo kargo = new Kargo();

        // Setter metotları ile değerleri ata
        kargo.setKargoId(1L);
        kargo.setKargoNo("K12345");
        kargo.setGondericiAdi("Ali");
        kargo.setGondericiSoyadi("Veli");
        kargo.setGondericiTelefon("1234567890");
        kargo.setAliciAdi("Ayşe");
        kargo.setAliciSoyadi("Demir");
        kargo.setAliciTelefon("0987654321");
        kargo.setAliciAdres("Ankara");
        kargo.setKargoDurumu("Yolda");
        kargo.setGonderimTarihi(LocalDateTime.of(2024, 12, 1, 10, 0));
        kargo.setTeslimTarihi(LocalDateTime.of(2024, 12, 2, 14, 0));
        kargo.setUrunler("[{\"urun\": \"Kitap\", \"adet\": 2}, {\"urun\": \"Laptop\", \"adet\": 1}]");
        kargo.setToplamAgirlik(new BigDecimal("10.50"));
        kargo.setUcret(new BigDecimal("250.75"));
        kargo.setOlusturmaTarihi(LocalDateTime.of(2024, 11, 30, 9, 0));
        kargo.setCreatedBy("Admin");
        kargo.setCreatedAt(LocalDateTime.of(2024, 11, 30, 9, 0));
        kargo.setUpdatedBy("Admin");
        kargo.setUpdatedAt(LocalDateTime.of(2024, 12, 1, 10, 0));
        kargo.setDeletedBy(null);
        kargo.setDeletedAt(null);
        kargo.setVersion(1);

        // Getter metotları ile değerleri kontrol et
        Assertions.assertEquals(1L, kargo.getKargoId());
        Assertions.assertEquals("K12345", kargo.getKargoNo());
        Assertions.assertEquals("Ali", kargo.getGondericiAdi());
        Assertions.assertEquals("Veli", kargo.getGondericiSoyadi());
        Assertions.assertEquals("1234567890", kargo.getGondericiTelefon());
        Assertions.assertEquals("Ayşe", kargo.getAliciAdi());
        Assertions.assertEquals("Demir", kargo.getAliciSoyadi());
        Assertions.assertEquals("0987654321", kargo.getAliciTelefon());
        Assertions.assertEquals("Ankara", kargo.getAliciAdres());
        Assertions.assertEquals("Yolda", kargo.getKargoDurumu());
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 1, 10, 0), kargo.getGonderimTarihi());
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 2, 14, 0), kargo.getTeslimTarihi());
        Assertions.assertEquals("[{\"urun\": \"Kitap\", \"adet\": 2}, {\"urun\": \"Laptop\", \"adet\": 1}]", kargo.getUrunler());
        Assertions.assertEquals(new BigDecimal("10.50"), kargo.getToplamAgirlik());
        Assertions.assertEquals(new BigDecimal("250.75"), kargo.getUcret());
        Assertions.assertEquals(LocalDateTime.of(2024, 11, 30, 9, 0), kargo.getOlusturmaTarihi());
        Assertions.assertEquals("Admin", kargo.getCreatedBy());
        Assertions.assertEquals(LocalDateTime.of(2024, 11, 30, 9, 0), kargo.getCreatedAt());
        Assertions.assertEquals("Admin", kargo.getUpdatedBy());
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 1, 10, 0), kargo.getUpdatedAt());
        Assertions.assertNull(kargo.getDeletedBy());
        Assertions.assertNull(kargo.getDeletedAt());
        Assertions.assertEquals(1, kargo.getVersion());
    }
}
