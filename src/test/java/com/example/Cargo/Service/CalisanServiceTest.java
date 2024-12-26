package com.example.Cargo.Service;

import com.example.Cargo.Entity.Calisan;
import com.example.Cargo.Repository.CalisanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalisanServiceTest {

    @Mock
    private CalisanRepository calisanRepository;

    @InjectMocks
    private CalisanService calisanService;

    private Calisan calisan;

    @BeforeEach
    public void setUp() {
        // Initializing the test Calisan object
        calisan = new Calisan();
        calisan.setId(1L);
        calisan.setAdi("Ahmet");
        calisan.setSoyadi("Yılmaz");
        calisan.setTelefon("5551234567");
        calisan.setPozisyon("Kargo Dağıtıcı");
        calisan.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveCalisan() {
        // Arrange
        when(calisanRepository.save(any(Calisan.class))).thenReturn(calisan);

        // Act
        Calisan savedCalisan = calisanService.saveCalisan(calisan);

        // Assert
        assertNotNull(savedCalisan);
        assertEquals("Ahmet", savedCalisan.getAdi());
        assertEquals("Yılmaz", savedCalisan.getSoyadi());
        assertEquals("5551234567", savedCalisan.getTelefon());
        assertEquals("Kargo Dağıtıcı", savedCalisan.getPozisyon());
        verify(calisanRepository, times(1)).save(any(Calisan.class)); // Verifying that save method was called
    }

    @Test
    public void testGetAllCalisans() {
        // Arrange
        when(calisanRepository.findAll()).thenReturn(Arrays.asList(calisan));

        // Act
        List<Calisan> calisanList = calisanService.getAllCalisans();

        // Assert
        assertNotNull(calisanList);
        assertEquals(1, calisanList.size());
        assertEquals("Ahmet", calisanList.get(0).getAdi());
        verify(calisanRepository, times(1)).findAll(); // Verifying that findAll method was called
    }

    @Test
    public void testDeleteCalisan() {
        // Arrange
        Long idToDelete = calisan.getId();
        doNothing().when(calisanRepository).deleteById(idToDelete);

        // Act
        calisanService.deleteCalisan(idToDelete);

        // Assert
        verify(calisanRepository, times(1)).deleteById(idToDelete); // Verifying that deleteById method was called
    }

    @Test
    public void testGetCalisanById() {
        // Arrange
        Long id = calisan.getId();
        when(calisanRepository.findById(id)).thenReturn(Optional.of(calisan));

        // Act
        Optional<Calisan> foundCalisan = calisanRepository.findById(id);

        // Assert
        assertTrue(foundCalisan.isPresent());
        assertEquals("Ahmet", foundCalisan.get().getAdi());
        assertEquals("Yılmaz", foundCalisan.get().getSoyadi());
        assertEquals("5551234567", foundCalisan.get().getTelefon());
        assertEquals("Kargo Dağıtıcı", foundCalisan.get().getPozisyon());
        verify(calisanRepository, times(1)).findById(id); // Verifying that findById method was called
    }
}
