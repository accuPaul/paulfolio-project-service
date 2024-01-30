package com.paulmount.paulfolioprojectservice.bootstrap;

import com.paulmount.paulfolioprojectservice.repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;

/**
 * *  Created by paulm on 1/24/2024
 */

//@Component
public class ProjectLoader implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    public ProjectLoader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
