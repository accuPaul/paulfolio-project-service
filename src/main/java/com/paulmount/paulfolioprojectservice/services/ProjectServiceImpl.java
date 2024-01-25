package com.paulmount.paulfolioprojectservice.services;

import com.paulmount.paulfolioprojectservice.domain.Project;
import com.paulmount.paulfolioprojectservice.repositories.ProjectRepository;
import com.paulmount.paulfolioprojectservice.web.controller.ItemNotFoundException;
import com.paulmount.paulfolioprojectservice.web.mappers.ProjectMapper;
import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
 * *  Created by paulm on 1/25/2024
 */

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public void deleteProject(UUID projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectDto updateProject(UUID projectId, ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(ItemNotFoundException::new);
        project.setProjectName(project.getProjectName());
        project.setDescription(project.getDescription());
        project.setProjectSource(projectDto.getProjectSource());
        project.setProjectUrl(projectDto.getProjectUrl());
        project.setLastModifiedDate(Timestamp.from(Instant.now()));
        project.setTags(projectDto.getTags());
        return projectMapper.projectToProjectDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto saveNewProject(ProjectDto projectDto) {
        Project project = projectMapper.projectDtoToProject(projectDto);
        return projectMapper.projectToProjectDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto getProjectById(UUID projectId) {
        return projectMapper.projectToProjectDto(
                projectRepository.findById(projectId).orElseThrow(ItemNotFoundException::new));
    }
}
