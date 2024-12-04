package com.example.Cargo.Service;

import com.example.Cargo.Entity.Calisan;
import com.example.Cargo.Repository.CalisanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalisanService {
    private final CalisanRepository calisanRepository;

    @Autowired
    public CalisanService(CalisanRepository calisanRepository) {
        this.calisanRepository = calisanRepository;
    }

    public Calisan saveCalisan(Calisan calisan) {
        return calisanRepository.save(calisan);
    }

    public List<Calisan> getAllCalisans() {
        return calisanRepository.findAll();
    }
    public void deleteCalisan(Long id) {
        calisanRepository.deleteById(id);
    }

}
