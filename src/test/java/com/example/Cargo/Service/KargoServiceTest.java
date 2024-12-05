package com.example.Cargo.Service;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Repository.KargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    private AutoCloseable closeable; // AutoCloseable için değişiklik

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); // Try-with-resources uyumu için closeable
    }

    @Test
    void testSaveKargo() {
        Kargo kargo = new Kargo();
        kargo.setKargoNo("K12345");

        when(kargoRepository.save(any(Kargo.class))).thenReturn(kargo);

        Kargo savedKargo = kargoService.saveKargo(kargo);

        assertNotNull(savedKargo);
        assertEquals("K12345", savedKargo.getKargoNo());
        verify(kargoRepository, times(1)).save(kargo);
    }

    @Test
    void testGetAllKargos() {
        Kargo kargo1 = new Kargo();
        kargo1.setKargoNo("K12345");

        Kargo kargo2 = new Kargo();
        kargo2.setKargoNo("K67890");

        when(kargoRepository.findAll()).thenReturn(Arrays.asList(kargo1, kargo2));

        List<Kargo> kargos = kargoService.getAllKargos();

        assertNotNull(kargos);
        assertEquals(2, kargos.size());
        verify(kargoRepository, times(1)).findAll();
    }

    @Test
    void testGetKargoById() {
        Long kargoId = 1L;
        Kargo kargo = new Kargo();
        kargo.setKargoNo("K12345");

        when(kargoRepository.findById(kargoId)).thenReturn(Optional.of(kargo));

        Optional<Kargo> foundKargo = kargoService.getKargoById(kargoId);

        assertTrue(foundKargo.isPresent());
        assertEquals("K12345", foundKargo.get().getKargoNo());
        verify(kargoRepository, times(1)).findById(kargoId);
    }

    @Test
    void testDeleteKargo() {
        Long kargoId = 1L;
        doNothing().when(kargoRepository).deleteById(kargoId);

        kargoService.deleteKargo(kargoId);

        verify(kargoRepository, times(1)).deleteById(kargoId);
    }
}
