package com.paulmount.paulfolioprojectservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulmount.paulfolioprojectservice.services.ProjectService;
import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import com.paulmount.paulfolioprojectservice.web.model.ProjectPagedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs()
@ComponentScan(basePackages = "com.paulmount.paulfolioproject.web.mappers.ProjectMapper")
@WebMvcTest
class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProjectService projectService;

    @Mock
    ProjectPagedList mockPagedList;

    @Mock
    PageRequest mockPageRequest;

    private final ProjectDto validProject = ProjectDto.builder().projectName("Test Name").description("Test Description").build();

    @Test
    void getProjectList() throws Exception {
        ProjectPagedList projectPagedList = new ProjectPagedList(Collections.singletonList(validProject));
        when(projectService.getProjectList(anyString(), anyString(), anyString(), any())).thenReturn(projectPagedList);

        ConstrainedFields fields = new ConstrainedFields(ProjectDto.class);

        mockMvc.perform(get("/api/v1/project")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("project-list"));
    }
    @Test
    void getProjectById() throws Exception {
        given(projectService.getProjectById(any())).willReturn(validProject);

        ConstrainedFields fields = new ConstrainedFields(ProjectDto.class);

        mockMvc.perform(get("/api/v1/project/{projectId}",UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("project-get",pathParameters(
                                parameterWithName("projectId").description("UUID of desired project")
                        ),
                        responseFields(
                                fields.withPath("id").description("Id of Project"),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").description("Date this was added to the list"),
                                fields.withPath("lastModifiedDate").description("Last updated here"),
                                fields.withPath("projectName").description("Name of the Project"),
                                fields.withPath("description").description("A brief description of what the project is/does"),
                                fields.withPath("projectUrl").description("A link to see the thing in action, if available"),
                                fields.withPath("projectSource").description("Link to the repository (only for public projects"),
                                fields.sectionWithPath("tags").description("A list of the various tech pieces used in the project")
                        )));
    }

    @Test
    void saveNewProject() throws Exception {
        ProjectDto projectDto = validProject;
        String projectJson = objectMapper.writeValueAsString(projectDto);

        given(projectService.saveNewProject(any())).willReturn(validProject);
        ConstrainedFields fields = new ConstrainedFields(ProjectDto.class);

        mockMvc.perform(post("/api/v1/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson))
                .andExpect(status().isCreated())
                .andDo(document("product-new",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").description("Date this was added to the list"),
                                fields.withPath("lastModifiedDate").description("Last updated here"),
                                fields.withPath("projectName").description("Name of the Project"),
                                fields.withPath("description").description("A brief description of what the project is/does"),
                                fields.withPath("projectUrl").description("A link to see the thing in action, if available"),
                                fields.withPath("projectSource").description("Link to the repository (only for public projects"),
                                fields.sectionWithPath("tags").description("A list of the various tech pieces used in the project")
                        ),
                        responseFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").description("Date this was added to the list"),
                                fields.withPath("lastModifiedDate").description("Last updated here"),
                                fields.withPath("projectName").description("Name of the Project"),
                                fields.withPath("description").description("A brief description of what the project is/does"),
                                fields.withPath("projectUrl").description("A link to see the thing in action, if available"),
                                fields.withPath("projectSource").description("Link to the repository (only for public projects"),
                                fields.sectionWithPath("tags").description("A list of the various tech pieces used in the project")
                        )));
    }

    @Test
    void updateProjectById() throws Exception {
        given(projectService.updateProject(any(), any())).willReturn(validProject);
        ProjectDto projectDto = validProject;
        String projectJson = objectMapper.writeValueAsString(projectDto);

        ConstrainedFields fields = new ConstrainedFields(ProjectDto.class);

        mockMvc.perform(put("/api/v1/project/{projectId}",UUID.randomUUID() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isOk())
                .andDo(document("project-update",pathParameters(
                                parameterWithName("projectId").description("UUID of desired project")
                        ),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").description("Date this was added to the list"),
                                fields.withPath("lastModifiedDate").description("Last updated here"),
                                fields.withPath("projectName").description("Name of the Project"),
                                fields.withPath("description").description("A brief description of what the project is/does"),
                                fields.withPath("projectUrl").description("A link to see the thing in action, if available"),
                                fields.withPath("projectSource").description("Link to the repository (only for public projects"),
                                fields.sectionWithPath("tags").description("A list of the various tech pieces used in the project")
                        ),responseFields(
                                fields.withPath("id").description("Id of Project"),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").description("Date this was added to the list"),
                                fields.withPath("lastModifiedDate").description("Last updated here"),
                                fields.withPath("projectName").description("Name of the Project"),
                                fields.withPath("description").description("A brief description of what the project is/does"),
                                fields.withPath("projectUrl").description("A link to see the thing in action, if available"),
                                fields.withPath("projectSource").description("Link to the repository (only for public projects"),
                                fields.sectionWithPath("tags").description("A list of the various tech pieces used in the project")
                        )));
    }

    @Test
    void deleteProject() throws Exception {

        mockMvc.perform(delete("/api/v1/project/{projectId}",UUID.randomUUID()))
                .andExpect(status().isNoContent())
                .andDo(document("project-delete",pathParameters(
                        parameterWithName("projectId").description("UUID of desired project")
                )));
    }

    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path),". ")));
        }
        private FieldDescriptor sectionWithPath(String path) {
            return subsectionWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path),". ")));
        }
    }
}