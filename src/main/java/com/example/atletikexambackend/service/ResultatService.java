package com.example.atletikexambackend.service;

import com.example.atletikexambackend.entity.Deltager;
import com.example.atletikexambackend.entity.Disciplin;
import com.example.atletikexambackend.entity.Resultat;
import com.example.atletikexambackend.repository.DeltagerRepository;
import com.example.atletikexambackend.repository.DisciplinRepository;
import com.example.atletikexambackend.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private DisciplinRepository disciplinRepository;

    public Resultat registrerResultat(Long deltagerId, Long disciplinId, double resultat) {
        Deltager deltager = deltagerRepository.findById(deltagerId)
                .orElseThrow(() -> new RuntimeException("Deltager ikke fundet med id : " + deltagerId));
        Disciplin disciplin = disciplinRepository.findById(disciplinId)
                .orElseThrow(() -> new RuntimeException("Disciplin ikke fundet med id : " + disciplinId));

        Resultat nytResultat = new Resultat();
        nytResultat.setDeltager(deltager);
        nytResultat.setDisciplin(disciplin);
        nytResultat.setResultat(resultat);

        return resultatRepository.save(nytResultat);
    }

    public List<Resultat> registrerFlereResultater(List<Resultat> resultater) {
        for (Resultat resultat : resultater) {
            Deltager deltager = deltagerRepository.findById(resultat.getDeltager().getId())
                    .orElseThrow(() -> new RuntimeException("Deltager ikke fundet med id : " + resultat.getDeltager().getId()));
            Disciplin disciplin = disciplinRepository.findById(resultat.getDisciplin().getId())
                    .orElseThrow(() -> new RuntimeException("Disciplin ikke fundet med id : " + resultat.getDisciplin().getId()));

            resultat.setDeltager(deltager);
            resultat.setDisciplin(disciplin);
        }

        return resultatRepository.saveAll(resultater);
    }

    public Resultat redigerResultat(Long resultatId, double nytResultat) {
        Resultat eksisterendeResultat = resultatRepository.findById(resultatId)
                .orElseThrow(() -> new RuntimeException("Resultat ikke fundet med id : " + resultatId));

        eksisterendeResultat.setResultat(nytResultat);

        return resultatRepository.save(eksisterendeResultat);
    }

    public void fjernResultat(Long resultatId) {
        resultatRepository.deleteById(resultatId);
    }

    public List<Resultat> visAlleResultaterForDisciplin(Long disciplinId) {
        return resultatRepository.findByDisciplinId(disciplinId);
    }

    public List<Resultat> visAlleResultater() {
        return resultatRepository.findAll();
    }

    public Resultat visResultat(Long id) {
        return resultatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultat ikke fundet med id : " + id));
    }
}