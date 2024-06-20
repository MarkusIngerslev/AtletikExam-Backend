package com.example.atletikexambackend.repository;

import com.example.atletikexambackend.entity.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinRepository extends JpaRepository<Disciplin, Long>{
    Disciplin findByNavn(String navn);
}
