package com.paulmount.paulfolioprojectservice.services;
/* Created by paulm on 1/25/2024*/

import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProjectService {
    void deleteProject(UUID projectId);

    ProjectDto updateProject(UUID projectId, ProjectDto projectDto);

    ProjectDto saveNewProject(ProjectDto projectDto);

    ProjectDto getProjectById(UUID projectId);
}
