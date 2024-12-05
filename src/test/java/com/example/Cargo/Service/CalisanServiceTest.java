package com.example.Cargo.Service;

import com.example.Cargo.Entity.Calisan;
import com.example.Cargo.Repository.CalisanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalisanServiceTest {

    @Mock
    private CalisanRepository calisanRepository;

    @InjectMocks
    private CalisanService calisanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCalisan() {
        // Arrange
        Calisan calisan = new Calisan();
        calisan.setAdi("Ali");
        calisan.setSoyadi("Yılmaz");
        calisan.setTelefon("123456789");
        calisan.setPozisyon("Yönetici");

        when(calisanRepository.save(any(Calisan.class))).thenReturn(calisan);

        // Act
        Calisan savedCalisan = calisanService.saveCalisan(calisan);

        // Assert
        assertNotNull(savedCalisan);
        assertEquals("Ali", savedCalisan.getAdi());
        assertEquals("Yılmaz", savedCalisan.getSoyadi());
        verify(calisanRepository, times(1)).save(calisan);
    }

    @Test
    void testGetAllCalisans() {
        // Arrange
        Calisan calisan1 = new Calisan();
        calisan1.setAdi("Ali");
        calisan1.setSoyadi("Yılmaz");

        Calisan calisan2 = new Calisan();
        calisan2.setAdi("Ayşe");
        calisan2.setSoyadi("Kara");

        List<Calisan> calisanList = Arrays.asList(calisan1, calisan2);

        when(calisanRepository.findAll()).thenReturn(calisanList);

        // Act
        List<Calisan> allCalisans = calisanService.getAllCalisans();

        // Assert
        assertNotNull(allCalisans);
        assertEquals(2, allCalisans.size());
        verify(calisanRepository, times(1)).findAll();
    }

    @Test
    void testDeleteCalisan() {
        // Arrange
        Long calisanId = 1L;
        doNothing().when(calisanRepository).deleteById(calisanId);

        // Act
        calisanService.deleteCalisan(calisanId);

        // Assert
        verify(calisanRepository, times(1)).deleteById(calisanId);
    }
}
