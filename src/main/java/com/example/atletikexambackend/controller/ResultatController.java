package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.DTO.ResultatDTO;
import com.example.atletikexambackend.entity.Resultat;
import com.example.atletikexambackend.service.ResultatService;
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

    @GetMapping("/disciplin/{disciplinNavn}")
    public ResponseEntity<List<Resultat>> visAlleResultaterForDisciplin(@PathVariable String disciplinNavn) {
        List<Resultat> resultater = resultatService.visAlleResultaterForDisciplin(disciplinNavn);
        return ResponseEntity.ok(resultater);
    }

    @PostMapping
    public ResponseEntity<Resultat> registrerResultat(@RequestBody Resultat resultat) {
        Resultat nytResultat = resultatService.registrerResultat(resultat);
        return new ResponseEntity<>(nytResultat, HttpStatus.CREATED);
    }

    @PostMapping("/flere")
    public ResponseEntity<List<Resultat>> registrerFlereResultater(@RequestBody List<Resultat> resultater) {
        List<Resultat> nyeResultater = resultatService.registrerFlereResultater(resultater);
        return new ResponseEntity<>(nyeResultater, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultat> opdaterResultat(@PathVariable Long id, @RequestBody ResultatDTO resultatDTO) {
        Resultat updateResultat = resultatService.opdaterResultat(id, resultatDTO);
        return ResponseEntity.ok(updateResultat);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Resultat> opdaterResultat(@PathVariable Long id  , @RequestBody Resultat resultat) {
//        try {
//            resultat.setId(id);
//            Resultat opdateretResultat = resultatService.opdaterResultat(resultat);
//            return ResponseEntity.ok(opdateretResultat);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> fjernResultat(@PathVariable Long id) {
        resultatService.fjernResultat(id);
        return ResponseEntity.noContent().build();
    }


}