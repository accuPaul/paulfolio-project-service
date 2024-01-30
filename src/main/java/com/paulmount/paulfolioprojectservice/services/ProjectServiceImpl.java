package com.paulmount.paulfolioprojectservice.services;

import com.paulmount.paulfolioprojectservice.domain.Project;
import com.paulmount.paulfolioprojectservice.repositories.ProjectRepository;
import com.paulmount.paulfolioprojectservice.web.controller.ItemNotFoundException;
import com.paulmount.paulfolioprojectservice.web.mappers.ProjectMapper;
import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import com.paulmount.paulfolioprojectservice.web.model.ProjectPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public ProjectPagedList getProjectList(String projectName, String url, String tags, PageRequest pageRequest) {
        ProjectPagedList projectPagedList;
        Page<Project> projectPage;


        if (StringUtils.hasText(projectName)) {
            projectPage = projectRepository.findAllByProjectNameContainingIgnoreCase(projectName, pageRequest);
        } else if (StringUtils.hasText(url)) {
            projectPage = projectRepository.findAllByProjectUrlContainingIgnoreCase(url, pageRequest);
        } else if (StringUtils.hasText(tags)) {
            List<String> tagList = Arrays.asList(StringUtils.commaDelimitedListToStringArray(tags));
            projectPage = projectRepository.findAllByTagsIn(tagList, pageRequest);
        } else {
            projectPage = projectRepository.findAll(pageRequest.withSort(Sort.Direction.DESC, "description"));
        }

        projectPagedList = new ProjectPagedList(projectPage
                .getContent()
                .stream()
                .map(projectMapper::projectToProjectDto)
                .collect(Collectors.toList()),
                PageRequest.of(projectPage.getPageable().getPageNumber(),
                        projectPage.getPageable().getPageSize()),
                projectPage.getTotalElements());

        return projectPagedList;
    }
}
