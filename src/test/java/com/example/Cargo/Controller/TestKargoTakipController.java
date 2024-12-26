package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Entity.KargoTakip;
import com.example.Cargo.Service.KargoTakipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KargoTakipController.class)
public class TestKargoTakipController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KargoTakipService kargoTakipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateKargoTakip() throws Exception {
        Kargo kargo = new Kargo();
        kargo.setKargoId(1L);

        KargoTakip kargoTakip = new KargoTakip();
        kargoTakip.setTakipId(1L);
        kargoTakip.setKargo(kargo);
        kargoTakip.setKonum("İstanbul");
        kargoTakip.setTarih(LocalDateTime.now());
        kargoTakip.setAciklama("Kargo yola çıktı.");

        when(kargoTakipService.saveKargoTakip(any(KargoTakip.class))).thenReturn(kargoTakip);

        mockMvc.perform(post("/api/kargoTakip")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kargoTakip)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.takipId", is(1)))
                .andExpect(jsonPath("$.kargo.kargoId", is(1)))
                .andExpect(jsonPath("$.konum", is("İstanbul")))
                .andExpect(jsonPath("$.aciklama", is("Kargo yola çıktı.")));

        verify(kargoTakipService, times(1)).saveKargoTakip(any(KargoTakip.class));
    }

    @Test
    void testGetKargoTakipByKargoId() throws Exception {
        Kargo kargo = new Kargo();
        kargo.setKargoId(1L);

        KargoTakip takip1 = new KargoTakip();
        takip1.setTakipId(1L);
        takip1.setKargo(kargo);
        takip1.setKonum("İstanbul");
        takip1.setTarih(LocalDateTime.now());
        takip1.setAciklama("Kargo yola çıktı.");

        KargoTakip takip2 = new KargoTakip();
        takip2.setTakipId(2L);
        takip2.setKargo(kargo);
        takip2.setKonum("Ankara");
        takip2.setTarih(LocalDateTime.now());
        takip2.setAciklama("Kargo şubeye ulaştı.");

        List<KargoTakip> takipList = Arrays.asList(takip1, takip2);

        when(kargoTakipService.getKargoTakipByKargoId(1L)).thenReturn(takipList);

        mockMvc.perform(get("/api/kargoTakip/kargo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].konum", is("İstanbul")))
                .andExpect(jsonPath("$[1].konum", is("Ankara")))
                .andExpect(jsonPath("$[1].aciklama", is("Kargo şubeye ulaştı.")));

        verify(kargoTakipService, times(1)).getKargoTakipByKargoId(1L);
    }
}
