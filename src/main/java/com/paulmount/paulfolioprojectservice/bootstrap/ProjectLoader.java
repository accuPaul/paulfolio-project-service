package com.paulmount.paulfolioprojectservice.bootstrap;

import com.paulmount.paulfolioprojectservice.domain.Project;
import com.paulmount.paulfolioprojectservice.repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * *  Created by paulm on 1/24/2024
 */

@Component
public class ProjectLoader implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    public ProjectLoader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProjects();
    }

    private void loadProjects() {
        if (projectRepository.count() == 0) {
            projectRepository.save(Project.builder()
                            .projectName("Paul's Portfolio")
                            .description("Portfolio of some nifty projects")
                            .tags(new ArrayList<>(Arrays.asList("JAVA","SPRING","Microservices")))
                    .build());
        }
    }
}
