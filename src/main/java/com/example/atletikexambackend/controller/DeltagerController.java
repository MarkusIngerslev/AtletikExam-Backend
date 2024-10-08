package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.entity.Deltager;
import com.example.atletikexambackend.service.DeltagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deltagere")
public class DeltagerController {

    @Autowired
    private DeltagerService deltagerService;

    @GetMapping
    public List<Deltager> findAlleDeltagere() {
        return deltagerService.findAlleDeltagere();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deltager> findDeltagerById(@PathVariable Long id) {
        Deltager deltager = deltagerService.findDeltagerById(id);
        return deltager != null ? ResponseEntity.ok(deltager) : ResponseEntity.notFound().build();
    }

    @GetMapping("/navn/{navn}")
    public List<Deltager> findDeltagerByName(@PathVariable String navn) {
        return deltagerService.findDeltagerByNavn(navn);
    }

    @PostMapping
    public ResponseEntity<Deltager> opretDeltager(@RequestBody Deltager deltager) {
        Deltager nyDeltager = deltagerService.opretDeltager(deltager);
        return new ResponseEntity<>(nyDeltager, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deltager> opdaterDeltager(@PathVariable Long id, @RequestBody Deltager deltager) {
        try {
            deltager.setId(id);
            Deltager opdateretDeltager = deltagerService.opdaterDeltager(deltager);
            return ResponseEntity.ok(opdateretDeltager);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> sletDeltager(@PathVariable Long id) {
        deltagerService.sletDeltager(id);
        return ResponseEntity.noContent().build();
    }
}
