package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Setting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Use this if using Mockito for mocking
@SpringBootTest // Use this for real Spring Boot integration tests
@DataJpaTest // Use this to test JPA Repositories with an embedded database like H2
public class TestSettingRepository {

    @Autowired
    private SettingRepository settingRepository;

    private Setting testSetting;

    @BeforeEach
    public void setUp() {
        testSetting = new Setting();
        testSetting.setSettingKey("exampleKey");
        testSetting.setSettingValue("exampleValue");
        testSetting.setCreatedAt(LocalDateTime.now());
        testSetting.setCreatedBy("Admin");
    }

    @Test
    public void testCreateSetting() {
        Setting savedSetting = settingRepository.createSetting(testSetting);
        assertNotNull(savedSetting);
        assertEquals("exampleKey", savedSetting.getSettingKey());
    }

    @Test
    public void testUpdateSetting() {
        Setting savedSetting = settingRepository.createSetting(testSetting);
        savedSetting.setSettingValue("updatedValue");

        Setting updatedSetting = settingRepository.updateSetting(savedSetting.getId(), savedSetting);

        assertNotNull(updatedSetting);
        assertEquals("updatedValue", updatedSetting.getSettingValue());
    }

    @Test
    public void testFindBySettingKey() {
        settingRepository.createSetting(testSetting);
        Optional<Setting> foundSetting = settingRepository.findBySettingKey("exampleKey");

        assertTrue(foundSetting.isPresent());
        assertEquals("exampleKey", foundSetting.get().getSettingKey());
    }

    @Test
    public void testDeleteSetting() {
        Setting savedSetting = settingRepository.createSetting(testSetting);
        settingRepository.deleteSetting(savedSetting.getId(), "Admin");

        Optional<Setting> deletedSetting = settingRepository.findById(savedSetting.getId());
        assertTrue(deletedSetting.isPresent());
        assertNotNull(deletedSetting.get().getDeletedAt());
    }

    @Test
    public void testDeleteSettingPermanently() {
        Setting savedSetting = settingRepository.createSetting(testSetting);
        settingRepository.deleteSettingPermanently(savedSetting.getId());

        Optional<Setting> deletedSetting = settingRepository.findById(savedSetting.getId());
        assertFalse(deletedSetting.isPresent());
    }
}
