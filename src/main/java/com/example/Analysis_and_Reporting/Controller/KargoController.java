package com.example.Analysis_and_Reporting.Controller;

import com.example.Analysis_and_Reporting.Entity.Kargo;
import com.example.Analysis_and_Reporting.Service.KargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kargo")
public class KargoController {
    private final KargoService kargoService;

    @Autowired
    public KargoController(KargoService kargoService) {
        this.kargoService = kargoService;
    }

    @PostMapping
    public Kargo createKargo(@RequestBody Kargo kargo) {
        return kargoService.saveKargo(kargo);
    }

    @GetMapping
    public List<Kargo> getAllKargos() {
        return kargoService.getAllKargos();
    }

    @GetMapping("/{id}")
    public Optional<Kargo> getKargoById(@PathVariable Long id) {
        return kargoService.getKargoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteKargo(@PathVariable Long id) {
        kargoService.deleteKargo(id);
    }
}
