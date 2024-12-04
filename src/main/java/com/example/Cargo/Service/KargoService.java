package com.example.Cargo.Service;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Repository.KargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KargoService {
    private final KargoRepository kargoRepository;

    @Autowired
    public KargoService(KargoRepository kargoRepository) {
        this.kargoRepository = kargoRepository;
    }

    public Kargo saveKargo(Kargo kargo) {
        return kargoRepository.save(kargo);
    }

    public List<Kargo> getAllKargos() {
        return kargoRepository.findAll();
    }

    public Optional<Kargo> getKargoById(Long id) {
        return kargoRepository.findById(id);
    }

    public void deleteKargo(Long id) {
        kargoRepository.deleteById(id);
    }
}
