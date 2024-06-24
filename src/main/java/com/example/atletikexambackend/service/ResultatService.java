package com.example.atletikexambackend.service;

import com.example.atletikexambackend.DTO.ResultatDTO;
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

    // Hent alle resultater
    public List<Resultat> visAlleResultater() {
        return resultatRepository.findAll();
    }

    // Hent et resultat fra id
    public Resultat visResultat(Long id) {
        return resultatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultat ikke fundet med id : " + id));
    }

    // Opret enkelt resultat
    public Resultat registrerResultat(Resultat resultat) {
                return resultatRepository.save(resultat);
    }

    // Opret flere resultater på en gang fra en liste
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



    // Opdater et resultat
    public Resultat opdaterResultat(Long id, ResultatDTO resultatDTO) {
        Resultat resultat = resultatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultat ikke fundet med id : " + id));

        if (resultatDTO.getResultattype() != null) {
            resultat.setResultattype(resultatDTO.getResultattype());
        }
        if (resultatDTO.getDato() != null) {
            resultat.setDato(resultatDTO.getDato());
        }
        if (resultatDTO.getResultatvalue() != null) {
            resultat.setResultatvalue(resultatDTO.getResultatvalue());
        }

        return resultatRepository.save(resultat);
    }

    // fjern et resultat
    public void fjernResultat(Long resultatId) {
        resultatRepository.deleteById(resultatId);
    }

    // find resultat ud fra disciplin
    public List<Resultat> visAlleResultaterForDisciplin(String disciplinNavn) {
        return resultatRepository.findByDisciplinNavn(disciplinNavn);
    }
}