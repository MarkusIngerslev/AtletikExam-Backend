package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.entity.Deltager;
import com.example.atletikexambackend.entity.Resultat;
import com.example.atletikexambackend.repository.DeltagerRepository;
import com.example.atletikexambackend.repository.ResultatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeltagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Autowired
    private ResultatRepository resultatRepository;

    private Long testDeltagerId;

    @BeforeEach
    public void setUp() throws Exception {
        // Opret en deltager til brug i testene
        Deltager deltager = new Deltager();
        deltager.setNavn("Test User");
        deltager.setKøn("M");
        deltager.setAlder(30);
        deltager.setKlub("Test Klub");
        Deltager savedDeltager = deltagerRepository.save(deltager);
        testDeltagerId = savedDeltager.getId();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Slet den test deltager der blev oprettet
        if (testDeltagerId != null) {
            deltagerRepository.deleteById(testDeltagerId);
        }
    }

//    @Test
//    public void opretDeltagerTest() throws Exception {
//        String deltagerJson = "{\"navn\": \"John Doe\", \"køn\": \"M\", \"alder\": 25, \"klub\": \"Klub A\"}";
//        mockMvc.perform(post("/api/deltagere")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(deltagerJson))
//                .andExpect(status().isCreated());
//    }

    @Test
    public void findAlleDeltagereTest() throws Exception {
        mockMvc.perform(get("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findDeltagerByIdTest() throws Exception {
        mockMvc.perform(get("/api/deltagere/" + testDeltagerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void opdaterDeltagerTest() throws Exception {
        Deltager deltager = new Deltager();
        deltager.setNavn("Updated User");
        deltager.setKøn("M");
        deltager.setAlder(35);
        deltager.setKlub("Updated Klub");

        mockMvc.perform(put("/api/deltagere/" + testDeltagerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltager)))
                .andExpect(status().isOk());
    }

    @Test
    public void sletDeltagerOgTjekResultaterTest() throws Exception {
        mockMvc.perform(delete("/api/deltagere/" + testDeltagerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        List<Resultat> resultater = resultatRepository.findByDeltagerId(testDeltagerId);
        assertTrue(resultater.isEmpty());
    }
}
