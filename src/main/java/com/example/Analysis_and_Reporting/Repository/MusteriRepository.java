package com.example.Analysis_and_Reporting.Repository;

import com.example.Analysis_and_Reporting.Entity.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Long> {
}
