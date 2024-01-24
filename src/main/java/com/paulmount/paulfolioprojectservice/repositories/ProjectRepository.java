package com.paulmount.paulfolioprojectservice.repositories;
/* Created by paulm on 1/24/2024*/

import com.paulmount.paulfolioprojectservice.domain.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProjectRepository extends PagingAndSortingRepository<Project, UUID> {
}
