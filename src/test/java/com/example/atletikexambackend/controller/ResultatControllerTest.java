package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.DTO.ResultatDTO;
import com.example.atletikexambackend.entity.Resultat;
import com.example.atletikexambackend.repository.ResultatRepository;
import com.example.atletikexambackend.service.ResultatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResultatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResultatService resultatService;

    @Autowired
    private ResultatRepository resultatRepository;

    private Resultat resultat;
    private Long testResultatId;
    // private String testResultDisciplin;

    @BeforeEach
    public void setUp() {
        resultat = new Resultat();
        resultat.setId(1L);
        // Set other properties of resultat as needed
        testResultatId = resultat.getId();
        // testResultDisciplin = resultat.getDisciplin().getNavn();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Slet det test resultat der blev oprettet
        if (testResultatId != null) {
            resultatRepository.deleteById(testResultatId);
        }
    }

    // Test for at hent alle resultater
    @Test
    public void testVisAlleResultater() throws Exception {
        when(resultatService.visAlleResultater()).thenReturn(Collections.singletonList(resultat));

        mockMvc.perform(get("/api/resultater")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Test for at hente et resultat
    @Test
    public void testVisResultat() throws Exception {
        when(resultatService.visResultat(anyLong())).thenReturn(resultat);

        mockMvc.perform(get("/api/resultater/" + testResultatId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Test for at registrere et resultat
    @Test
    public void testRegistrerResultat() throws Exception {
        when(resultatService.registrerResultat(any(Resultat.class))).thenReturn(resultat);

        mockMvc.perform(post("/api/resultater")
                        .content(new ObjectMapper().writeValueAsString(resultat))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Test for at registrere flere resultater
    @Test
    public void testRegistrerFlereResultater() throws Exception {
        when(resultatService.registrerFlereResultater(any(List.class))).thenReturn(Collections.singletonList(resultat));

        mockMvc.perform(post("/api/resultater/flere")
                        .content(new ObjectMapper().writeValueAsString(Collections.singletonList(resultat)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Test for at opdatere et resultat

    @Test
    public void testOpdaterResultat() throws Exception {
        when(resultatService.opdaterResultat(anyLong(), any(ResultatDTO.class))).thenReturn(resultat);

        mockMvc.perform(put("/api/resultater/" + testResultatId)
                        .content(new ObjectMapper().writeValueAsString(resultat))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    public void testOpdaterResultat() throws Exception {
//        when(resultatService.opdaterResultat(any(Resultat.class))).thenReturn(resultat);
//
//        mockMvc.perform(put("/api/resultater/" + testResultatId)
//                        .content(new ObjectMapper().writeValueAsString(resultat))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

    // Test for at fjerne et resultat
    @Test
    public void testFjernResultat() throws Exception {
        mockMvc.perform(delete("/api/resultater/" + testResultatId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // Test for at hente alle resultater for en disciplin
    @Test
    public void testVisAlleResultaterForDisciplin() throws Exception {
        String disciplinNavn = "Hammerkast";
        when(resultatService.visAlleResultaterForDisciplin(disciplinNavn)).thenReturn(Collections.singletonList(resultat));

        mockMvc.perform(get("/api/resultater/disciplin/" + disciplinNavn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}