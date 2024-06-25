package com.example.atletikexambackend.service;

import com.example.atletikexambackend.entity.Disciplin;
import com.example.atletikexambackend.repository.DisciplinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinService {

    @Autowired
    private DisciplinRepository disciplinRepository;

    public Disciplin findDisciplinById(Long id) {
        return disciplinRepository.findById(id).orElse(null);
    }

    public List<Disciplin> findAlleDiscipliner() {
        return disciplinRepository.findAll();
    }

    public void sletDisciplin(Long id) {
        disciplinRepository.deleteById(id);
    }
}
