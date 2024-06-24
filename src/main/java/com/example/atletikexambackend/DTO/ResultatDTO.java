package com.example.atletikexambackend.DTO;

import com.example.atletikexambackend.entity.Resultat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Removes all properties with null values
public class ResultatDTO {
    private String resultattype;
    private String dato;
    private String resultatvalue;

    public ResultatDTO(Resultat r) {
        this.resultattype = r.getResultattype();
        this.dato = r.getDato();
        this.resultatvalue = r.getResultatvalue();
    }
}
