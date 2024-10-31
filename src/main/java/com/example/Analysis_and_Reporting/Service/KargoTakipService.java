package com.example.Analysis_and_Reporting.Service;

import com.example.Analysis_and_Reporting.Entity.KargoTakip;
import com.example.Analysis_and_Reporting.Repository.KargoTakipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KargoTakipService {
    private final KargoTakipRepository kargoTakipRepository;

    @Autowired
    public KargoTakipService(KargoTakipRepository kargoTakipRepository) {
        this.kargoTakipRepository = kargoTakipRepository;
    }

    public KargoTakip saveKargoTakip(KargoTakip kargoTakip) {
        return kargoTakipRepository.save(kargoTakip);
    }

    public List<KargoTakip> getKargoTakipByKargoId(Long kargoId) {
        return kargoTakipRepository.findByKargo_KargoId(kargoId);
    }
}
