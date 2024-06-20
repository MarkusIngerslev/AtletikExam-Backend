package com.example.atletikexambackend.repository;

import com.example.atletikexambackend.entity.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeltagerRepository extends JpaRepository<Deltager, Long> {
}
