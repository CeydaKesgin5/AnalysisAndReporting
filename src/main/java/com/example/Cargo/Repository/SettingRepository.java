package com.example.Cargo.Repository;

import com.example.Cargo.Entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    // Setting key'e göre bir ayar getir
    Optional<Setting> findBySettingKey(String settingKey);

    // Silinmiş ayarları listele (soft delete)
    List<Setting> findByDeletedAtNotNull();
}
