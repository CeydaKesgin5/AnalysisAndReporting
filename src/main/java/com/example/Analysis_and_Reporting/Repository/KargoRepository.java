package com.example.Analysis_and_Reporting.Repository;

import com.example.Analysis_and_Reporting.Entity.Kargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KargoRepository extends JpaRepository<Kargo, Long> {
    // Kargo numarasına göre arama metodu
    Optional<Kargo> findByKargoNo(String kargoNo);
}
