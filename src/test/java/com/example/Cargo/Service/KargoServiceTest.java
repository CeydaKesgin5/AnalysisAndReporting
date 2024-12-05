package com.example.Cargo.Service;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Repository.KargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KargoServiceTest {

    @Mock
    private KargoRepository kargoRepository;

    @InjectMocks
    private KargoService kargoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveKargo() {
        // Arrange
        Kargo kargo = new Kargo();
        kargo.setKargoNo("K12345");
        kargo.setGondericiAdi("Ahmet");
        kargo.setAliciAdi("Mehmet");
        kargo.setUcret(BigDecimal.valueOf(100.50));

        when(kargoRepository.save(any(Kargo.class))).thenReturn(kargo);

        // Act
        Kargo savedKargo = kargoService.saveKargo(kargo);

        // Assert
        assertNotNull(savedKargo);
        assertEquals("K12345", savedKargo.getKargoNo());
        assertEquals("Ahmet", savedKargo.getGondericiAdi());
        assertEquals("Mehmet", savedKargo.getAliciAdi());
        verify(kargoRepository, times(1)).save(kargo);
    }

    @Test
    void testGetAllKargos() {
        // Arrange
        Kargo kargo1 = new Kargo();
        kargo1.setKargoNo("K12345");

        Kargo kargo2 = new Kargo();
        kargo2.setKargoNo("K67890");

        List<Kargo> kargoList = Arrays.asList(kargo1, kargo2);

        when(kargoRepository.findAll()).thenReturn(kargoList);

        // Act
        List<Kargo> allKargos = kargoService.getAllKargos();

        // Assert
        assertNotNull(allKargos);
        assertEquals(2, allKargos.size());
        verify(kargoRepository, times(1)).findAll();
    }

    @Test
    void testGetKargoById() {
        // Arrange
        Long kargoId = 1L;
        Kargo kargo = new Kargo();
        kargo.setKargoNo("K12345");

        when(kargoRepository.findById(kargoId)).thenReturn(Optional.of(kargo));

        // Act
        Optional<Kargo> foundKargo = kargoService.getKargoById(kargoId);

        // Assert
        assertTrue(foundKargo.isPresent());
        assertEquals("K12345", foundKargo.get().getKargoNo());
        verify(kargoRepository, times(1)).findById(kargoId);
    }

    @Test
    void testDeleteKargo() {
        // Arrange
        Long kargoId = 1L;
        doNothing().when(kargoRepository).deleteById(kargoId);

        // Act
        kargoService.deleteKargo(kargoId);

        // Assert
        verify(kargoRepository, times(1)).deleteById(kargoId);
    }
}
