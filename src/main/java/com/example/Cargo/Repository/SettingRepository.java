package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
    @Query("select a.settingKey from Setting a WHERE a.settingKey= :settingKey")

    Optional<Setting> findBySettingKey(String settingKey);

    default Setting createSetting(Setting setting) {
        setting.setCreatedAt(java.time.LocalDateTime.now());
        setting.setCreatedBy("Admin");
        return save(setting);
    }
    default Setting updateSetting(Long id, Setting updatedSetting) {
        Setting existingSetting = findById(id).orElseThrow(() -> new RuntimeException("Setting bulunamadı."));
        existingSetting.setSettingKey(updatedSetting.getSettingKey());
        existingSetting.setSettingValue(updatedSetting.getSettingValue());
        existingSetting.setUpdatedAt(java.time.LocalDateTime.now());
        existingSetting.setUpdatedBy("Admin");
        return save(existingSetting);
    }

    default void deleteSetting(Long id, String deletedBy) {
        Setting existingSetting = findById(id).orElseThrow(() -> new RuntimeException("Setting bulunamadı."));
        existingSetting.setDeletedAt(java.time.LocalDateTime.now());
        existingSetting.setDeletedBy(deletedBy);
        save(existingSetting);
    }

    @Modifying
    @Transactional
    @Query("DELETE FROM Setting s WHERE s.id = :id")
    void deleteSettingPermanently(@Param("id") Long id);
}
