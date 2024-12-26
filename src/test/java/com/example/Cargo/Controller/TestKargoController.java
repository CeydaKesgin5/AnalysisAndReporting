package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Service.KargoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KargoController.class)
public class TestKargoController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KargoService kargoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateKargo() throws Exception {
        Kargo kargo = new Kargo();
        kargo.setKargoId(1L);
        kargo.setKargoNo("KRG12345");
        kargo.setGondericiAdi("Ali");
        kargo.setGondericiSoyadi("Veli");
        kargo.setGondericiTelefon("1234567890");
        kargo.setAliciAdi("Mehmet");
        kargo.setAliciSoyadi("Demir");
        kargo.setAliciTelefon("0987654321");
        kargo.setAliciAdres("İstanbul, Türkiye");
        kargo.setKargoDurumu("Hazırlanıyor");
        kargo.setToplamAgirlik(new BigDecimal("5.25"));
        kargo.setUcret(new BigDecimal("25.50"));
        kargo.setOlusturmaTarihi(LocalDateTime.now());

        when(kargoService.saveKargo(any(Kargo.class))).thenReturn(kargo);

        mockMvc.perform(post("/api/kargo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kargo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kargoId", is(1)))
                .andExpect(jsonPath("$.kargoNo", is("KRG12345")))
                .andExpect(jsonPath("$.gondericiAdi", is("Ali")))
                .andExpect(jsonPath("$.aliciAdi", is("Mehmet")))
                .andExpect(jsonPath("$.toplamAgirlik", is(5.25)))
                .andExpect(jsonPath("$.ucret", is(25.50)));

        verify(kargoService, times(1)).saveKargo(any(Kargo.class));
    }

    @Test
    void testGetAllKargos() throws Exception {
        Kargo kargo1 = new Kargo();
        kargo1.setKargoId(1L);
        kargo1.setKargoNo("KRG12345");
        kargo1.setGondericiAdi("Ali");
        kargo1.setAliciAdi("Mehmet");

        Kargo kargo2 = new Kargo();
        kargo2.setKargoId(2L);
        kargo2.setKargoNo("KRG67890");
        kargo2.setGondericiAdi("Ayşe");
        kargo2.setAliciAdi("Fatma");

        List<Kargo> kargoList = Arrays.asList(kargo1, kargo2);

        when(kargoService.getAllKargos()).thenReturn(kargoList);

        mockMvc.perform(get("/api/kargo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].kargoNo", is("KRG12345")))
                .andExpect(jsonPath("$[1].gondericiAdi", is("Ayşe")));

        verify(kargoService, times(1)).getAllKargos();
    }

    @Test
    void testGetKargoById() throws Exception {
        Kargo kargo = new Kargo();
        kargo.setKargoId(1L);
        kargo.setKargoNo("KRG12345");
        kargo.setGondericiAdi("Ali");
        kargo.setAliciAdi("Mehmet");

        when(kargoService.getKargoById(1L)).thenReturn(Optional.of(kargo));

        mockMvc.perform(get("/api/kargo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kargoNo", is("KRG12345")))
                .andExpect(jsonPath("$.gondericiAdi", is("Ali")))
                .andExpect(jsonPath("$.aliciAdi", is("Mehmet")));

        verify(kargoService, times(1)).getKargoById(1L);
    }

    @Test
    void testDeleteKargo() throws Exception {
        doNothing().when(kargoService).deleteKargo(1L);

        mockMvc.perform(delete("/api/kargo/1"))
                .andExpect(status().isOk());

        verify(kargoService, times(1)).deleteKargo(1L);
    }
}
