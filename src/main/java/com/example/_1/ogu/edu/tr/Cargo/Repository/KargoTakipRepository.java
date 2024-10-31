package _1.ogu.edu.tr.Analysis_and_Reporting.repository;


import _1.ogu.edu.tr.Analysis_and_Reporting.entity.KargoTakip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KargoTakipRepository extends JpaRepository<KargoTakip, Long> {
    List<KargoTakip> findByKargo_KargoId(Long kargoId);
}