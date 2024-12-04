package com.example.Cargo.Controller;

import com.example.Cargo.DTO.SettingDto;
import com.example.Cargo.Entity.Setting;
import com.example.Cargo.Service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    @Autowired
    private SettingService settingService;

    // Tüm ayarları almak için
    @GetMapping
    public List<Setting> getAllSettings() {
        return settingService.getAllSettings();
    }

    // ID'ye göre ayar almak için
    @GetMapping("/{id}")
    public ResponseEntity<Setting> getSettingById(@PathVariable Long id) {
        Optional<Setting> setting = settingService.getSettingById(id);
        return setting.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Yeni bir ayar oluşturmak için
    @PostMapping
    public Setting createSetting(@RequestBody SettingDto settingDto) {
        return settingService.createSetting(settingDto);
    }

    // Var olan bir ayarı güncellemek için
    @PutMapping("/{id}")
    public ResponseEntity<Setting> updateSetting(@PathVariable Long id, @RequestBody SettingDto settingDto) {
        try {
            Setting updatedSetting = settingService.updateSetting(id, settingDto);
            return ResponseEntity.ok(updatedSetting);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ID'ye göre ayarı silmek için
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable Long id) {
        settingService.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}
