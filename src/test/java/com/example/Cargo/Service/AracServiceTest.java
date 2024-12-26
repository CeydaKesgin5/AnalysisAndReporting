package com.example.Cargo.Service;

import com.example.Cargo.Entity.Arac;
import com.example.Cargo.Repository.AracRepository;
import com.example.Cargo.Config.RestClientConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class AracServiceTest {

    @Mock
    private AracRepository aracRepository;

    @Mock
    private RestClientConfig restClientConfig;

    @InjectMocks
    private AracService aracService;

    private Arac arac;

    @BeforeEach
    public void setUp() {
        // Initializing the test Arac object
        arac = new Arac();
        arac.setId(1L);
        arac.setModel("Ford Transit");
    }

    @Test
    public void testSaveArac() {
        // Arrange
        when(aracRepository.save(any(Arac.class))).thenReturn(arac);

        // Act
        Arac savedArac = aracService.saveArac(arac);

        // Assert
        assertNotNull(savedArac);
        assertEquals("Ford Transit", savedArac.getModel());
        verify(aracRepository, times(1)).save(any(Arac.class)); // Verifying that save method was called
    }

    @Test
    public void testGetAllAracs() {
        // Arrange
        when(aracRepository.findAll()).thenReturn(Arrays.asList(arac));

        // Act
        List<Arac> aracList = aracService.getAllAracs();

        // Assert
        assertNotNull(aracList);
        assertEquals(1, aracList.size());
        assertEquals("Ford Transit", aracList.get(0).getModel());
        verify(aracRepository, times(1)).findAll(); // Verifying that findAll method was called
    }

    @Test
    public void testDeleteArac() {
        // Arrange
        Long idToDelete = arac.getId();
        doNothing().when(aracRepository).deleteById(idToDelete);

        // Act
        aracService.deleteArac(idToDelete);

        // Assert
        verify(aracRepository, times(1)).deleteById(idToDelete); // Verifying that deleteById method was called
    }

    @Test
    public void testGetAracById() {
        // Arrange
        Long id = arac.getId();
        when(aracRepository.findById(id)).thenReturn(Optional.of(arac));

        // Act
        Optional<Arac> foundArac = aracRepository.findById(id);

        // Assert
        assertTrue(foundArac.isPresent());
        assertEquals("Ford Transit", foundArac.get().getModel());
        verify(aracRepository, times(1)).findById(id); // Verifying that findById method was called
    }
}
