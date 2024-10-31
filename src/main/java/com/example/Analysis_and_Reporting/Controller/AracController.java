package com.example.Analysis_and_Reporting.Controller;

import com.example.Analysis_and_Reporting.Entity.Arac;
import com.example.Analysis_and_Reporting.Service.AracService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arac")
public class AracController {
    private final AracService aracService;

    @Autowired
    public AracController(AracService aracService) {
        this.aracService = aracService;
    }

    @PostMapping
    public Arac createArac(@RequestBody Arac arac) {
        return aracService.saveArac(arac);
    }

    @GetMapping
    public List<Arac> getAllAraclar() {
        return aracService.getAllAracs();
    }

    @GetMapping("/{id}")
    public Arac getAracById(@PathVariable Long id) {
        return aracService.getAllAracs().stream()
                .filter(arac -> arac.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Arac updateArac(@PathVariable Long id, @RequestBody Arac arac) {
        arac.setId(id);
        return aracService.saveArac(arac);
    }

    @DeleteMapping("/{id}")
    public void deleteArac(@PathVariable Long id) {
        aracService.deleteArac(id);
    }
}

