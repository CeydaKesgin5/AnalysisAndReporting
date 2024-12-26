package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Arac;
import com.example.Cargo.Service.AracService;
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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AracController.class)
public class TestAracController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AracService aracService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateArac() throws Exception {
        Arac arac = new Arac();
        arac.setId(1L);
        arac.setPlaka("34ABC123");
        arac.setModel("Ford Transit");
        arac.setYukKapasitesi(1200);
        arac.setCreatedBy("TestUser");
        arac.setCreatedAt(LocalDateTime.now());

        when(aracService.saveArac(any(Arac.class))).thenReturn(arac);

        mockMvc.perform(post("/api/arac")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(arac)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.plaka", is("34ABC123")))
                .andExpect(jsonPath("$.model", is("Ford Transit")))
                .andExpect(jsonPath("$.yukKapasitesi", is(1200.0)));

        verify(aracService, times(1)).saveArac(any(Arac.class));
    }

    @Test
    void testGetAllAraclar() throws Exception {
        Arac arac1 = new Arac();
        arac1.setId(1L);
        arac1.setPlaka("34ABC123");
        arac1.setModel("Ford Transit");
        arac1.setYukKapasitesi(1200);

        Arac arac2 = new Arac();
        arac2.setId(2L);
        arac2.setPlaka("35XYZ456");
        arac2.setModel("Mercedes Sprinter");
        arac2.setYukKapasitesi(1500);

        List<Arac> aracList = Arrays.asList(arac1, arac2);

        when(aracService.getAllAracs()).thenReturn(aracList);

        mockMvc.perform(get("/api/arac"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].plaka", is("34ABC123")))
                .andExpect(jsonPath("$[1].model", is("Mercedes Sprinter")));
    }

    @Test
    void testGetAracById() throws Exception {
        Arac arac = new Arac();
        arac.setId(1L);
        arac.setPlaka("34ABC123");
        arac.setModel("Ford Transit");
        arac.setYukKapasitesi(1200);

        when(aracService.getAllAracs()).thenReturn(List.of(arac));

        mockMvc.perform(get("/api/arac/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plaka", is("34ABC123")))
                .andExpect(jsonPath("$.model", is("Ford Transit")))
                .andExpect(jsonPath("$.yukKapasitesi", is(1200.0)));
    }

    @Test
    void testUpdateArac() throws Exception {
        Arac updatedArac = new Arac();
        updatedArac.setId(1L);
        updatedArac.setPlaka("34DEF456");
        updatedArac.setModel("Renault Master");
        updatedArac.setYukKapasitesi(1300);

        when(aracService.saveArac(any(Arac.class))).thenReturn(updatedArac);

        mockMvc.perform(put("/api/arac/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedArac)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plaka", is("34DEF456")))
                .andExpect(jsonPath("$.model", is("Renault Master")))
                .andExpect(jsonPath("$.yukKapasitesi", is(1300.0)));

        verify(aracService, times(1)).saveArac(any(Arac.class));
    }

    @Test
    void testDeleteArac() throws Exception {
        doNothing().when(aracService).deleteArac(1L);

        mockMvc.perform(delete("/api/arac/1"))
                .andExpect(status().isOk());

        verify(aracService, times(1)).deleteArac(1L);
    }
}
