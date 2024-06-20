package com.example.atletikexambackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class DeltagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void opretDeltagerTest() throws Exception {
        String deltagerJson = "{\"navn\": \"John Doe\", \"køn\": \"M\", \"alder\": 25, \"klub\": \"Klub A\"}";
        mockMvc.perform(post("/api/deltagere")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deltagerJson))
                .andExpect(status().isCreated());
    }
}
