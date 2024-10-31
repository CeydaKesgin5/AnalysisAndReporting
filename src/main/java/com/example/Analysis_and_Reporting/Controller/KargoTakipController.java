package com.example.Analysis_and_Reporting.Controller;

import com.example.Analysis_and_Reporting.Entity.KargoTakip;

import com.example.Analysis_and_Reporting.Service.KargoTakipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kargoTakip")
public class KargoTakipController {
    private final KargoTakipService kargoTakipService;

    @Autowired
    public KargoTakipController(KargoTakipService kargoTakipService) {
        this.kargoTakipService = kargoTakipService;
    }

    @PostMapping
    public KargoTakip createKargoTakip(@RequestBody KargoTakip kargoTakip) {
        return kargoTakipService.saveKargoTakip(kargoTakip);
    }

    @GetMapping("/kargo/{kargoId}")
    public List<KargoTakip> getKargoTakipByKargoId(@PathVariable Long kargoId) {
        return kargoTakipService.getKargoTakipByKargoId(kargoId);
    }
}
