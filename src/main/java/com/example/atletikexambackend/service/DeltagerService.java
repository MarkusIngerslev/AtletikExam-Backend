package com.example.atletikexambackend.service;

import com.example.atletikexambackend.entity.Deltager;
import com.example.atletikexambackend.repository.DeltagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeltagerService {
    @Autowired
    private DeltagerRepository deltagerRepository;

    public Deltager opretDeltager(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }

    public List<Deltager> findAlleDeltagere() {
        return deltagerRepository.findAll();
    }

    public Deltager findDeltagerById(Long id) {
        return deltagerRepository.findById(id).orElse(null);
    }

    public Deltager opdaterDeltager(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }

    public void sletDeltager(Long id) {
        deltagerRepository.deleteById(id);
    }
}
