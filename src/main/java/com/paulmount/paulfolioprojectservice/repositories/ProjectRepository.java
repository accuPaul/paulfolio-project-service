package com.paulmount.paulfolioprojectservice.repositories;
/* Created by paulm on 1/24/2024*/

import com.paulmount.paulfolioprojectservice.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends PagingAndSortingRepository<Project, UUID>, CrudRepository<Project, UUID> {
    Page<Project> findAllByProjectNameContainingIgnoreCase(String projectName, PageRequest pageRequest);
    Page<Project> findAllByProjectUrlContainingIgnoreCase(String url, PageRequest pageRequest);

    Page<Project> findAllByTagsIn(List<String> tag, PageRequest pageRequest);

}
