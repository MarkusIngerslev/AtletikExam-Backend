package com.example.atletikexambackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultattype;
    private LocalDate dato;
    private String resultatvalue;

    @ManyToOne
    @JoinColumn(name = "deltager_id")
    @JsonBackReference
    private Deltager deltager;

    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;
}
