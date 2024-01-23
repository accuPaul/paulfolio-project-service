package com.paulmount.paulfolioprojectservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulmount.paulfolioprojectservice.model.ProjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void getProjectById() throws Exception {
        mockMvc.perform(get("/api/v1/project/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewProject() throws Exception {
        ProjectDto projectDto = ProjectDto.builder().id(UUID.randomUUID()).build();
        String projectJson = objectMapper.writeValueAsString(projectDto);

        mockMvc.perform(post("/api/v1/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateProjectById() throws Exception {
        ProjectDto projectDto = ProjectDto.builder().build();
        String projectJson = objectMapper.writeValueAsString(projectDto);

        mockMvc.perform(put("/api/v1/project/"+UUID.randomUUID() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProject() throws Exception {
        mockMvc.perform(delete("/api/v1/project/"+UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}