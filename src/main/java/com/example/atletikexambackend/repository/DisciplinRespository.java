package com.example.atletikexambackend.repository;

import com.example.atletikexambackend.entity.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinRespository extends JpaRepository<Disciplin, Long> {
}
