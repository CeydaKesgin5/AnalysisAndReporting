package com.example.Cargo.Entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KargoTakipTest {

    @Test
    void testKargoTakipEntity() {
        KargoTakip kargoTakip = new KargoTakip();
        kargoTakip.setKonum("İstanbul");
        kargoTakip.setTarih(LocalDateTime.now());
        kargoTakip.setAciklama("Kargo yola çıktı");

        // Assertions
        assertEquals("İstanbul", kargoTakip.getKonum());
        assertEquals("Kargo yola çıktı", kargoTakip.getAciklama());
        // assertNotNull(kargoTakip.getTarih());
    }
}
