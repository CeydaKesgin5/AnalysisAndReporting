package com.example.Analysis_and_Reporting.Service;

import com.example.Analysis_and_Reporting.Entity.Arac;
import com.example.Analysis_and_Reporting.Repository.AracRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AracService {
    private final AracRepository aracRepository;

    @Autowired
    public AracService(AracRepository aracRepository) {
        this.aracRepository = aracRepository;
    }

    public Arac saveArac(Arac arac) {
        return aracRepository.save(arac);
    }

    public List<Arac> getAllAracs() {
        return aracRepository.findAll();
    }

    public void deleteArac(Long id) {
        aracRepository.deleteById(id);
    }
}
