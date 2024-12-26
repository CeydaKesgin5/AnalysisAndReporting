package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Kargo;
import com.example.Cargo.Service.KargoService;
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

    @PostMapping("create")
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
