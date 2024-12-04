package com.example.Cargo.Service;

import com.example.Cargo.DTO.SettingDto;
import com.example.Cargo.Entity.Setting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SettingServiceTest {

    static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @Autowired
    private SettingService settingService;

    @BeforeEach
    public void setUp() {
        postgresContainer.start();
        settingService.deleteAllSettings(); // Test başlamadan önce tüm kayıtları temizle
    }

    @AfterEach
    public void tearDown() {
        postgresContainer.stop();
    }

    @Test
    public void testSaveSetting() {
        // Arrange
        Setting setting = new Setting();
        setting.setSettingKey("theme");
        setting.setSettingValue("dark");

        // Act
        settingService.saveSetting(setting);

        // Assert
        Optional<Setting> savedSetting = settingService.getSettingById(setting.getId());
        assertThat(savedSetting).isPresent();
        assertThat(savedSetting.get().getSettingKey()).isEqualTo("theme");
        assertThat(savedSetting.get().getSettingValue()).isEqualTo("dark");
    }

    @Test
    public void testGetAllSettings() {
        // Arrange
        Setting setting1 = new Setting();
        setting1.setSettingKey("theme");
        setting1.setSettingValue("dark");

        Setting setting2 = new Setting();
        setting2.setSettingKey("language");
        setting2.setSettingValue("en");

        settingService.saveSetting(setting1);
        settingService.saveSetting(setting2);

        // Act
        var allSettings = settingService.getAllSettings();

        // Assert
        assertThat(allSettings).hasSize(2);
        assertThat(allSettings).extracting("settingKey").contains("theme", "language");
    }

    @Test
    public void testUpdateSetting() {
        // Arrange
        Setting setting = new Setting();
        setting.setSettingKey("theme");
        setting.setSettingValue("dark");

        settingService.saveSetting(setting);

        // Act
        SettingDto settingDto = new SettingDto();
        settingDto.setSettingValue("light");
        settingService.updateSetting(setting.getId(), settingDto);

        // Assert
        Optional<Setting> updatedSetting = settingService.getSettingById(setting.getId());
        assertThat(updatedSetting).isPresent();
        assertThat(updatedSetting.get().getSettingValue()).isEqualTo("light");
    }


    @Test
    public void testDeleteSetting() {
        // Arrange
        Setting setting = new Setting();
        setting.setSettingKey("theme");
        setting.setSettingValue("dark");

        settingService.saveSetting(setting);

        // Act
        settingService.deleteSetting(setting.getId());

        // Assert
        Optional<Setting> deletedSetting = settingService.getSettingById(setting.getId());
        assertThat(deletedSetting).isEmpty();
    }

    @Test
    public void testSettingNotFound() {
        // Act
        Optional<Setting> setting = settingService.getSettingById(999L);

        // Assert
        assertThat(setting).isEmpty();
    }
}