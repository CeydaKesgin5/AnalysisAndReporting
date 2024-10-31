package com.example.Analysis_and_Reporting.Controller;

import com.example.Analysis_and_Reporting.Entity.Musteri;
import com.example.Analysis_and_Reporting.Service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musteri")
public class MusteriController {
    private final MusteriService musteriService;

    @Autowired
    public MusteriController(MusteriService musteriService) {
        this.musteriService = musteriService;
    }

    @PostMapping
    public Musteri createMusteri(@RequestBody Musteri musteri) {
        return musteriService.saveMusteri(musteri);
    }

    @GetMapping
    public List<Musteri> getAllMusteriler() {
        return musteriService.getAllMusteris();
    }

    @GetMapping("/{id}")
    public Musteri getMusteriById(@PathVariable Long id) {
        return musteriService.getAllMusteris().stream()
                .filter(musteri -> musteri.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Musteri updateMusteri(@PathVariable Long id, @RequestBody Musteri musteri) {
        musteri.setId(id);
        return musteriService.saveMusteri(musteri);
    }

    @DeleteMapping("/{id}")
    public void deleteMusteri(@PathVariable Long id) {
        musteriService.deleteMusteri(id);
    }
}
