package com.example.Analysis_and_Reporting.Repository;

import com.example.Analysis_and_Reporting.Entity.KargoTakip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KargoTakipRepository extends JpaRepository<KargoTakip, Long> {
    List<KargoTakip> findByKargo_KargoId(Long kargoId);
}