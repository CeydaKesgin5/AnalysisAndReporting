package com.example.Cargo.Controller;

import com.example.Cargo.Entity.Calisan;
import com.example.Cargo.Service.CalisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calisan")
public class CalisanController {
    private final CalisanService calisanService;

    @Autowired
    public CalisanController(CalisanService calisanService) {
        this.calisanService = calisanService;
    }

    @PostMapping
    public Calisan createCalisan(@RequestBody Calisan calisan) {
        return calisanService.saveCalisan(calisan);
    }

    @GetMapping
    public List<Calisan> getAllCalisanlar() {
        return calisanService.getAllCalisans();
    }

    @GetMapping("/{id}")
    public Calisan getCalisanById(@PathVariable Long id) {
        return calisanService.getAllCalisans().stream()
                .filter(calisan -> calisan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Calisan updateCalisan(@PathVariable Long id, @RequestBody Calisan calisan) {
        calisan.setId(id);
        return calisanService.saveCalisan(calisan);
    }

    @DeleteMapping("/{id}")
    public void deleteCalisan(@PathVariable Long id) {
        calisanService.deleteCalisan(id);
    }
}
