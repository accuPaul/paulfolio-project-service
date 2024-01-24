package com.paulmount.paulfolioprojectservice.web.controller;

import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("projectId") UUID projectId) {
        return new ResponseEntity<>(ProjectDto.builder().id(projectId).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveNewProject(@Valid @RequestBody ProjectDto projectDto) {
        ProjectDto newProject = projectDto;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/project/"+UUID.randomUUID().toString());

        return new ResponseEntity<>(newProject, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProjectById(@PathVariable("projectId") UUID projectId, @Valid @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("projectId") UUID projectId) {
        // todo implement delete
    }
}
