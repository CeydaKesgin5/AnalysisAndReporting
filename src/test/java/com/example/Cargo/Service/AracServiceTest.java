package com.example.Cargo.Service;

import com.example.Cargo.Entity.Arac;
import com.example.Cargo.Repository.AracRepository;
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

class AracServiceTest {

    @Mock
    private AracRepository aracRepository;

    @InjectMocks
    private AracService aracService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveArac() {
        // Arrange
        Arac arac = new Arac();
        arac.setPlaka("34ABC123");
        arac.setModel("Toyota");
        arac.setYukKapasitesi(1500);

        when(aracRepository.save(any(Arac.class))).thenReturn(arac);

        // Act
        Arac savedArac = aracService.saveArac(arac);

        // Assert
        assertNotNull(savedArac);
        assertEquals("34ABC123", savedArac.getPlaka());
        assertEquals("Toyota", savedArac.getModel());
        verify(aracRepository, times(1)).save(arac);
    }

    @Test
    void testGetAllAracs() {
        // Arrange
        Arac arac1 = new Arac();
        arac1.setPlaka("34ABC123");
        arac1.setModel("Toyota");

        Arac arac2 = new Arac();
        arac2.setPlaka("35XYZ987");
        arac2.setModel("Honda");

        List<Arac> aracList = Arrays.asList(arac1, arac2);

        when(aracRepository.findAll()).thenReturn(aracList);

        // Act
        List<Arac> allAracs = aracService.getAllAracs();

        // Assert
        assertNotNull(allAracs);
        assertEquals(2, allAracs.size());
        verify(aracRepository, times(1)).findAll();
    }
    @Test
    void testDeleteArac() {
        // Arrange
        Long aracId = 1L;
        doNothing().when(aracRepository).deleteById(aracId);

        // Act
        aracService.deleteArac(aracId);

        // Assert
        verify(aracRepository, times(1)).deleteById(aracId);
    }

}
