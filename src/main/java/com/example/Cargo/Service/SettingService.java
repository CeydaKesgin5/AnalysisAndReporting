package com.example.Cargo.Service;

import com.example.Cargo.DTO.SettingDto;
import com.example.Cargo.Entity.Setting;
import com.example.Cargo.Repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;
    public Setting saveSetting(Setting setting) {
        return settingRepository.save(setting);
    }
    // Tüm ayarları listelemek için
    public List<Setting> getAllSettings() {
        return settingRepository.findAll();
    }

    // ID'ye göre ayar bulmak için
    public Optional<Setting> getSettingById(Long id) {
        return settingRepository.findById(id);
    }

    // Yeni bir ayar oluşturmak için
    public Setting createSetting(SettingDto settingDto) {
        Setting setting = new Setting();
        setting.setSettingKey(settingDto.getSettingKey());
        setting.setSettingValue(settingDto.getSettingValue());
        return settingRepository.save(setting);
    }

    // Ayarı güncellemek için
    public Setting updateSetting(Long id, SettingDto settingDto) {
        Optional<Setting> existingSetting = settingRepository.findById(id);
        if (existingSetting.isPresent()) {
            Setting setting = existingSetting.get();
            setting.setSettingKey(settingDto.getSettingKey());
            setting.setSettingValue(settingDto.getSettingValue());
            return settingRepository.save(setting);
        }
        throw new RuntimeException("Setting not found");
    }

    // Ayarı silmek için
    public void deleteSetting(Long id) {
        settingRepository.deleteById(id);
    }
    public void deleteAllSettings() {
        List<Setting> allSettings = settingRepository.findAll();
        for (Setting setting : allSettings) {
            deleteSetting(setting.getId());
        }
    }

}
