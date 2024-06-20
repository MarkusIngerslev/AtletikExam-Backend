package com.example.atletikexambackend.controller;

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

    @BeforeEach
    public void setUp() {
        resultat = new Resultat();
        resultat.setId(1L);
        // Set other properties of resultat as needed
        testResultatId = resultat.getId();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Slet det test resultat der blev oprettet
        if (testResultatId != null) {
            resultatRepository.deleteById(testResultatId);
        }
    }

    @Test
    public void testRegistrerResultat() throws Exception {
        when(resultatService.registrerResultat(anyLong(), anyLong(), anyDouble())).thenReturn(resultat);

        mockMvc.perform(post("/api/resultater")
                        .param("deltagerId", "1")
                        .param("disciplinId", "1")
                        .param("resultat", "10.5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegistrerFlereResultater() throws Exception {
        when(resultatService.registrerFlereResultater(any(List.class))).thenReturn(Collections.singletonList(resultat));

        mockMvc.perform(post("/api/resultater/flere")
                        .content(new ObjectMapper().writeValueAsString(Collections.singletonList(resultat)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRedigerResultat() throws Exception {
        when(resultatService.redigerResultat(anyLong(), anyDouble())).thenReturn(resultat);

        mockMvc.perform(put("/api/resultater/1")
                        .param("nytResultat", "10.5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFjernResultat() throws Exception {
        mockMvc.perform(delete("/api/resultater/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testVisAlleResultaterForDisciplin() throws Exception {
        when(resultatService.visAlleResultaterForDisciplin(anyLong())).thenReturn(Collections.singletonList(resultat));

        mockMvc.perform(get("/api/resultater/disciplin/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}