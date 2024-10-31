package com.example.Analysis_and_Reporting.Repository;

import com.example.Analysis_and_Reporting.Entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
}
