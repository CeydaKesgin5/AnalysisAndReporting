package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Calisan;
import com.example.Cargo.Service.CalisanService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalisanController.class)
public class TestCalisanController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalisanService calisanService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCalisan() throws Exception {
        Calisan calisan = new Calisan();
        calisan.setId(1L);
        calisan.setAdi("Ali");
        calisan.setSoyadi("Veli");
        calisan.setTelefon("1234567890");
        calisan.setPozisyon("Kargo Çalışanı");
        calisan.setCreatedBy("Admin");
        calisan.setCreatedAt(LocalDateTime.now());

        when(calisanService.saveCalisan(any(Calisan.class))).thenReturn(calisan);

        mockMvc.perform(post("/api/calisan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calisan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.adi", is("Ali")))
                .andExpect(jsonPath("$.soyadi", is("Veli")))
                .andExpect(jsonPath("$.telefon", is("1234567890")))
                .andExpect(jsonPath("$.pozisyon", is("Kargo Çalışanı")));

        verify(calisanService, times(1)).saveCalisan(any(Calisan.class));
    }

    @Test
    void testGetAllCalisanlar() throws Exception {
        Calisan calisan1 = new Calisan();
        calisan1.setId(1L);
        calisan1.setAdi("Ali");
        calisan1.setSoyadi("Veli");
        calisan1.setTelefon("1234567890");
        calisan1.setPozisyon("Kargo Çalışanı");

        Calisan calisan2 = new Calisan();
        calisan2.setId(2L);
        calisan2.setAdi("Ayşe");
        calisan2.setSoyadi("Fatma");
        calisan2.setTelefon("0987654321");
        calisan2.setPozisyon("Şoför");

        List<Calisan> calisanList = Arrays.asList(calisan1, calisan2);

        when(calisanService.getAllCalisans()).thenReturn(calisanList);

        mockMvc.perform(get("/api/calisan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].adi", is("Ali")))
                .andExpect(jsonPath("$[1].pozisyon", is("Şoför")));
    }

    @Test
    void testGetCalisanById() throws Exception {
        Calisan calisan = new Calisan();
        calisan.setId(1L);
        calisan.setAdi("Ali");
        calisan.setSoyadi("Veli");
        calisan.setTelefon("1234567890");
        calisan.setPozisyon("Kargo Çalışanı");

        when(calisanService.getAllCalisans()).thenReturn(List.of(calisan));

        mockMvc.perform(get("/api/calisan/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adi", is("Ali")))
                .andExpect(jsonPath("$.telefon", is("1234567890")));
    }

    @Test
    void testUpdateCalisan() throws Exception {
        Calisan updatedCalisan = new Calisan();
        updatedCalisan.setId(1L);
        updatedCalisan.setAdi("Mehmet");
        updatedCalisan.setSoyadi("Demir");
        updatedCalisan.setTelefon("5432167890");
        updatedCalisan.setPozisyon("Depo Görevlisi");

        when(calisanService.saveCalisan(any(Calisan.class))).thenReturn(updatedCalisan);

        mockMvc.perform(put("/api/calisan/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCalisan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adi", is("Mehmet")))
                .andExpect(jsonPath("$.pozisyon", is("Depo Görevlisi")));

        verify(calisanService, times(1)).saveCalisan(any(Calisan.class));
    }

    @Test
    void testDeleteCalisan() throws Exception {
        doNothing().when(calisanService).deleteCalisan(1L);

        mockMvc.perform(delete("/api/calisan/1"))
                .andExpect(status().isOk());

        verify(calisanService, times(1)).deleteCalisan(1L);
    }
}
