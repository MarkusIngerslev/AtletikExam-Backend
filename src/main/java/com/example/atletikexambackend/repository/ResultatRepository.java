package com.example.atletikexambackend.repository;

import com.example.atletikexambackend.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findByDeltagerId(Long deltagerId);

    List<Resultat> findByDisciplinId(Long disciplinId);
}
