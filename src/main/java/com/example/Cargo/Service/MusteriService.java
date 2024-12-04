package com.example.Cargo.Service;

import com.example.Cargo.Entity.Musteri;
import com.example.Cargo.Repository.MusteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MusteriService {
    private final MusteriRepository musteriRepository;

    @Autowired
    public MusteriService(MusteriRepository musteriRepository) {
        this.musteriRepository = musteriRepository;
    }

    public Musteri saveMusteri(Musteri musteri) {
        return musteriRepository.save(musteri);
    }

    public List<Musteri> getAllMusteris() {
        return musteriRepository.findAll();
    }

    public Optional<Musteri> getMusteriById(Long id) {
        return musteriRepository.findById(id);
    }

    public Musteri updateMusteri(Musteri musteri) {
        // Müşteri var mı kontrol et
        Optional<Musteri> existingMusteri = musteriRepository.findById(musteri.getId());
        if (existingMusteri.isPresent()) {
            return musteriRepository.save(musteri); // Müşteri güncelle
        } else {
            throw new RuntimeException("Müşteri bulunamadı"); // Müşteri bulunamazsa hata fırlat
        }
    }
    public void deleteAllMusteri() {
        musteriRepository.deleteAll();
    }

    public void deleteMusteri(Long id) {
        musteriRepository.deleteById(id);
    }
}
