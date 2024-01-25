package com.paulmount.paulfolioprojectservice.web.controller;

import com.paulmount.paulfolioprojectservice.services.ProjectService;
import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
