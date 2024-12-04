package com.example.Cargo.Service;

import com.example.Cargo.Entity.Arac;
import com.example.Cargo.Repository.AracRepository;
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
