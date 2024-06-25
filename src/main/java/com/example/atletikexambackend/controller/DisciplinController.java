package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.entity.Disciplin;
import com.example.atletikexambackend.service.DisciplinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discipliner")
public class DisciplinController {

    @Autowired
    private DisciplinService disciplinService;

    @GetMapping
    public List<Disciplin> findAlleDiscipliner() {
        return disciplinService.findAlleDiscipliner();
    }

    @GetMapping("/{id}")
    public Disciplin findDisciplinById(@PathVariable Long id) {
        return disciplinService.findDisciplinById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> sletDisciplin(@PathVariable Long id) {
        disciplinService.sletDisciplin(id);
        return ResponseEntity.noContent().build();
    }

}
