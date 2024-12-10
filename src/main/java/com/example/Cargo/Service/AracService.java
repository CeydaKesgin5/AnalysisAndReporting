package com.example.Cargo.Service;

import com.example.Cargo.Config.RestClientConfig;
import com.example.Cargo.Entity.Arac;
import com.example.Cargo.Repository.AracRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AracService {
    private final AracRepository aracRepository;
    private final RestClientConfig restClient;

    @Autowired
    public AracService(AracRepository aracRepository, RestClientConfig restClient) {
        this.aracRepository = aracRepository;
        this.restClient = restClient;
    }


    // restClient.Get().RestClient

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
