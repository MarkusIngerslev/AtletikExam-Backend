package com.example.atletikexambackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Deltager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private String køn;
    private int alder;
    private String klub;

    @OneToMany(mappedBy = "deltager", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Resultat> resultater;
}
