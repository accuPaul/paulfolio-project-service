package com.paulmount.paulfolioprojectservice.web.mappers;/* Created by paulm on 1/24/2024*/

import com.paulmount.paulfolioprojectservice.domain.Project;
import com.paulmount.paulfolioprojectservice.web.model.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface ProjectMapper {
    ProjectDto projectToProjectDto(Project project);

    Project projectDtoToProject(ProjectDto projectDto);
}
