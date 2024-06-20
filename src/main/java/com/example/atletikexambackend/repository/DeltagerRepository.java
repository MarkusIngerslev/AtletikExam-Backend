package com.example.atletikexambackend.repository;

import com.example.atletikexambackend.entity.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeltagerRepository extends JpaRepository<Deltager, Long> {

}
