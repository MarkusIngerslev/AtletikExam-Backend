package com.example.atletikexambackend.service;

import com.example.atletikexambackend.entity.Deltager;
import com.example.atletikexambackend.entity.Resultat;
import com.example.atletikexambackend.repository.DeltagerRepository;
import com.example.atletikexambackend.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeltagerService {
    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private ResultatRepository resultatRepository;

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
        Deltager eksisterendeDeltager = deltagerRepository.findById(deltager.getId())
                .orElseThrow(() -> new RuntimeException("Deltager ikke fundet med id : " + deltager.getId()));

        eksisterendeDeltager.setNavn(deltager.getNavn());
        eksisterendeDeltager.setKøn(deltager.getKøn());
        eksisterendeDeltager.setAlder(deltager.getAlder());
        eksisterendeDeltager.setKlub(deltager.getKlub());

        return deltagerRepository.save(eksisterendeDeltager);
    }

    public void sletDeltager(Long id) {
        List<Resultat> resultater = resultatRepository.findByDeltagerId(id);
        resultatRepository.deleteAll(resultater);
        deltagerRepository.deleteById(id);
    }

    public List<Deltager> findDeltagerByNavn(String navn) {
        return deltagerRepository.findDeltagerByNavn(navn);
    }
}
