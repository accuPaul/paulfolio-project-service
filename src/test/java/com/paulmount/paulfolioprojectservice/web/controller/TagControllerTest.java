package com.paulmount.paulfolioprojectservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulmount.paulfolioprojectservice.services.ProjectService;
import com.paulmount.paulfolioprojectservice.web.model.TagDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@WithMockUser
class TagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProjectService projectService;

    private final TagDto validTagDto = TagDto.builder().tagName("TEST").count(0).build();

    @Test
    void getTags() throws Exception {
        List<TagDto> validList = new ArrayList<>(Collections.singletonList(validTagDto));
        when(projectService.getTagList()).thenReturn(validList);

        mockMvc.perform(get("/api/v1/tags")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getTagCount() throws Exception {

        given(projectService.getTag(any())).willReturn(validTagDto);

        mockMvc.perform(get("/api/v1/tags/{tagName}",anyString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}