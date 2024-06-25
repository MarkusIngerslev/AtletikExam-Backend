package com.example.atletikexambackend.controller;


import com.example.atletikexambackend.entity.Disciplin;
import com.example.atletikexambackend.repository.DisciplinRepository;
import com.example.atletikexambackend.service.DisciplinService;
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
public class DisciplinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinService disciplinService;

    @Autowired
    private DisciplinRepository disciplinRepository;

    private Disciplin disciplin;
    private Long testDisciplinId;


    @BeforeEach
    public void setUp() {
        disciplin = new Disciplin();
        disciplin.setId(1L);
        // Set other properties of disciplin as needed
        testDisciplinId = disciplin.getId();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Slet det test disciplin der blev oprettet
        if (testDisciplinId != null) {
             disciplinRepository.deleteById(testDisciplinId);
        }
    }

    // Test for at hent alle discipliner
    @Test
    public void testFindAlleDiscipliner() throws  Exception{
        when(disciplinService.findAlleDiscipliner()).thenReturn(Collections.singletonList(disciplin));

        mockMvc.perform(get("/api/discipliner")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Test for at hente en disciplin
    @Test
    public void testFindDisciplinById() throws Exception {
        when(disciplinService.findDisciplinById(anyLong())).thenReturn(disciplin);

        mockMvc.perform(get("/api/discipliner/" + testDisciplinId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Test for at fjerne en disciplin
//    @Test
//    public void testSletDisciplin() throws Exception {
//        mockMvc.perform(delete("/api/discipliner/" + testDisciplinId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
}
