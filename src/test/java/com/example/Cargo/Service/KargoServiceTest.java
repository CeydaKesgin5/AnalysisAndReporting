package com.example.Cargo.Service;

import org.springframework.web.reactive.function.client.WebClient;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Repository.KargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class KargoServiceTest {

    @Mock
    private KargoRepository kargoRepository;

    @Mock
    private WebClient.Builder webClientBuilder;  // Mock WebClient builder

    @Mock
    private WebClient webClient;  // Mock WebClient

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;  // Mock for URI specification

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;  // Mock for request body specification

    @Mock
    private WebClient.ResponseSpec responseSpec;  // Mock for response handling

    @InjectMocks
    private KargoService kargoService;

    private Kargo kargo;

    private  RestClient restClient;

    @BeforeEach
    public void setUp() {
        // Initializing the test Kargo object
        kargo = new Kargo();
        kargo.setKargoId(1L);
        kargo.setKargoNo("KARGO123");
        kargo.setGondericiAdi("Ahmet");
        kargo.setGondericiSoyadi("Yılmaz");
        kargo.setGondericiTelefon("5551234567");
        kargo.setAliciAdi("Mehmet");
        kargo.setAliciSoyadi("Demir");
        kargo.setAliciTelefon("5559876543");
        kargo.setAliciAdres("İstanbul, Türkiye");
        kargo.setUrunler("Ürün1, Ürün2");
        kargo.setToplamAgirlik(BigDecimal.valueOf(10.5));
        kargo.setUcret(BigDecimal.valueOf(150.00));
        kargo.setGonderimTarihi(LocalDateTime.now());
        kargo.setTeslimTarihi(LocalDateTime.now().plusDays(2));
        kargo.setOlusturmaTarihi(LocalDateTime.now());
    }

    @Test
    public void testSaveKargo() {
        // Arrange
        when(kargoRepository.save(any(Kargo.class))).thenReturn(kargo);

        // Act
        Kargo savedKargo = kargoService.saveKargo(kargo);

        // Assert
        assertNotNull(savedKargo);
        assertEquals("KARGO123", savedKargo.getKargoNo());
        assertEquals("Ahmet", savedKargo.getGondericiAdi());
        verify(kargoRepository, times(1)).save(any(Kargo.class)); // Verifying that save method was called
    }

    @Test
    public void testGetAllKargos() {
        // Arrange
        when(kargoRepository.findAll()).thenReturn(java.util.Collections.singletonList(kargo));

        // Act
        var kargos = kargoService.getAllKargos();

        // Assert
        assertNotNull(kargos);
        assertEquals(1, kargos.size());
        assertEquals("KARGO123", kargos.get(0).getKargoNo());
        verify(kargoRepository, times(1)).findAll(); // Verifying that findAll method was called
    }

    @Test
    public void testGetKargoById() {
        // Arrange
        when(kargoRepository.findById(1L)).thenReturn(Optional.of(kargo));

        // Act
        Optional<Kargo> foundKargo = kargoService.getKargoById(1L);

        // Assert
        assertTrue(foundKargo.isPresent());
        assertEquals("KARGO123", foundKargo.get().getKargoNo());
        verify(kargoRepository, times(1)).findById(1L); // Verifying that findById method was called
    }

    @Test
    public void testDeleteKargo() {
        // Arrange
        Long idToDelete = kargo.getKargoId();
        doNothing().when(kargoRepository).deleteById(idToDelete);

        // Act
        kargoService.deleteKargo(idToDelete);

        // Assert
        verify(kargoRepository, times(1)).deleteById(idToDelete); // Verifying that deleteById method was called
    }

    @Test
    public void testCreateNewKargo_ExceptionHandling() {
        // Arrange
        when(restClient.post()).thenThrow(new RuntimeException("Error during creating Kargo"));

        // Act
        Kargo createdKargo = kargoService.createNewKargo(kargo);

        // Assert
        assertNull(createdKargo);
    }
}
