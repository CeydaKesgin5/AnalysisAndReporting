package com.example.Cargo.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalisanTest {

    @Test
    void testCalisanEntity() {
        Calisan calisan = new Calisan();
        calisan.setAdi("Ali");
        calisan.setSoyadi("Veli");
        calisan.setTelefon("555-123-4567");
        calisan.setPozisyon("Yönetici");

        // Assertions
        assertEquals("Ali", calisan.getAdi());
        assertEquals("Veli", calisan.getSoyadi());
        assertEquals("555-123-4567", calisan.getTelefon());
        assertEquals("Yönetici", calisan.getPozisyon());
    }
}
