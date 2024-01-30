package com.paulmount.paulfolioprojectservice.web.controller;

import com.paulmount.paulfolioprojectservice.services.ProjectService;
import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import com.paulmount.paulfolioprojectservice.web.model.ProjectPagedList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;
    private final static Integer DEFAULT_PAGE_NUMBER = 0;
    private final static Integer DEFAULT_PAGE_SIZE = 20;

    @GetMapping
    public ResponseEntity<ProjectPagedList> getProjects(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                        @RequestParam(value = "projectName", required = false) String projectName,
                                                        @RequestParam(value = "url", required = false) String url,
                                                        @RequestParam(value = "tags", required = false) String tags) {

        if (pageNumber == null || pageNumber < 0) pageNumber = DEFAULT_PAGE_NUMBER;
        if (pageSize == null || pageSize < 0) pageSize = DEFAULT_PAGE_SIZE;

        ProjectPagedList projectList = projectService.getProjectList(projectName, url, tags, PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(projectList, HttpStatus.OK);

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("projectId") UUID projectId) {
        return new ResponseEntity<>(projectService.getProjectById(projectId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveNewProject(@Valid @RequestBody ProjectDto projectDto) {
        ProjectDto newProject = projectService.saveNewProject(projectDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/project/"+newProject.getId());

        return new ResponseEntity<>(newProject, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProjectById(@PathVariable("projectId") UUID projectId, @Valid @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.updateProject(projectId, projectDto),HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("projectId") UUID projectId) {
        projectService.deleteProject(projectId);
    }
}
