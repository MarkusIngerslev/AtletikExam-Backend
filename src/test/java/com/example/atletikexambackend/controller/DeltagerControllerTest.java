package com.example.atletikexambackend.controller;

import com.example.atletikexambackend.entity.Deltager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class DeltagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void opretDeltagerTest() throws Exception {
        String deltagerJson = "{\"navn\": \"John Doe\", \"køn\": \"M\", \"alder\": 25, \"klub\": \"Klub A\"}";
        mockMvc.perform(post("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deltagerJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void findAlleDeltagereTest() throws Exception {
        mockMvc.perform(get("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findDeltagerByIdTest() throws Exception {
        mockMvc.perform(get("/api/deltagere/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void opdaterDeltagerTest() throws Exception {
        Deltager deltager = new Deltager();
        deltager.setNavn("John Doe");
        deltager.setKøn("M");
        deltager.setAlder(25);
        deltager.setKlub("Klub A");

        mockMvc.perform(put("/api/deltagere/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deltager)))
                .andExpect(status().isOk());
    }

    @Test
    public void sletDeltagerTest() throws Exception {
        mockMvc.perform(delete("/api/deltagere/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
