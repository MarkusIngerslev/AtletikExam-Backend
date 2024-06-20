package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.entity.Resultat;
import com.example.atletikexambackend.service.ResultatService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultater")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @GetMapping
    public ResponseEntity<List<Resultat>> visAlleResultater() {
        List<Resultat> resultater = resultatService.visAlleResultater();
        return ResponseEntity.ok(resultater);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> visResultat(@PathVariable Long id) {
        Resultat resultat = resultatService.visResultat(id);
        return ResponseEntity.ok(resultat);
    }

    @GetMapping("/disciplin/{id}")
    public ResponseEntity<List<Resultat>> visAlleResultaterForDisciplin(@PathVariable Long id) {
        List<Resultat> resultater = resultatService.visAlleResultaterForDisciplin(id);
        return ResponseEntity.ok(resultater);
    }

    @PostMapping
    public ResponseEntity<Resultat> registrerResultat(@RequestParam Long deltagerId, @RequestParam Long disciplinId, @RequestParam double resultat) {
        Resultat nytResultat = resultatService.registrerResultat(deltagerId, disciplinId, resultat);
        return new ResponseEntity<>(nytResultat, HttpStatus.CREATED);
    }

    @PostMapping("/flere")
    public ResponseEntity<List<Resultat>> registrerFlereResultater(@RequestBody List<Resultat> resultater) {
        List<Resultat> nyeResultater = resultatService.registrerFlereResultater(resultater);
        return new ResponseEntity<>(nyeResultater, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultat> redigerResultat(@PathVariable Long id, @RequestParam double nytResultat) {
        Resultat opdateretResultat = resultatService.redigerResultat(id, nytResultat);
        return ResponseEntity.ok(opdateretResultat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> fjernResultat(@PathVariable Long id) {
        resultatService.fjernResultat(id);
        return ResponseEntity.noContent().build();
    }


}