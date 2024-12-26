package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Musteri;
import com.example.Cargo.Service.MusteriService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MusteriController.class)
public class TestMusteriController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MusteriService musteriService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateMusteri() throws Exception {
        Musteri musteri = new Musteri("Ali", "Veli", "5551234567", "Ankara");
        musteri.setId(1L);

        when(musteriService.saveMusteri(any(Musteri.class))).thenReturn(musteri);

        mockMvc.perform(post("/api/musteri")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musteri)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.adi", is("Ali")))
                .andExpect(jsonPath("$.soyadi", is("Veli")))
                .andExpect(jsonPath("$.telefon", is("5551234567")))
                .andExpect(jsonPath("$.adres", is("Ankara")));

        verify(musteriService, times(1)).saveMusteri(any(Musteri.class));
    }

    @Test
    void testGetAllMusteriler() throws Exception {
        Musteri musteri1 = new Musteri("Ali", "Veli", "5551234567", "Ankara");
        musteri1.setId(1L);
        Musteri musteri2 = new Musteri("Ahmet", "Kaya", "5559876543", "İstanbul");
        musteri2.setId(2L);

        List<Musteri> musteriler = Arrays.asList(musteri1, musteri2);

        when(musteriService.getAllMusteris()).thenReturn(musteriler);

        mockMvc.perform(get("/api/musteri"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].adi", is("Ali")))
                .andExpect(jsonPath("$[1].adi", is("Ahmet")));

        verify(musteriService, times(1)).getAllMusteris();
    }

    @Test
    void testGetMusteriById() throws Exception {
        Musteri musteri = new Musteri("Ali", "Veli", "5551234567", "Ankara");
        musteri.setId(1L);

        when(musteriService.getAllMusteris()).thenReturn(List.of(musteri));

        mockMvc.perform(get("/api/musteri/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.adi", is("Ali")));

        verify(musteriService, times(1)).getAllMusteris();
    }

    @Test
    void testUpdateMusteri() throws Exception {
        Musteri updatedMusteri = new Musteri("Ali", "Yılmaz", "5551112222", "İzmir");
        updatedMusteri.setId(1L);

        when(musteriService.saveMusteri(any(Musteri.class))).thenReturn(updatedMusteri);

        mockMvc.perform(put("/api/musteri/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMusteri)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.soyadi", is("Yılmaz")))
                .andExpect(jsonPath("$.adres", is("İzmir")));

        verify(musteriService, times(1)).saveMusteri(any(Musteri.class));
    }

    @Test
    void testDeleteMusteri() throws Exception {
        doNothing().when(musteriService).deleteMusteri(1L);

        mockMvc.perform(delete("/api/musteri/1"))
                .andExpect(status().isOk());

        verify(musteriService, times(1)).deleteMusteri(1L);
    }
}
