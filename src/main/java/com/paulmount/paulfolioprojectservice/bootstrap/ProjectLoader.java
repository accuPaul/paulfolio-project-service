package com.paulmount.paulfolioprojectservice.bootstrap;

import com.paulmount.paulfolioprojectservice.domain.Project;
import com.paulmount.paulfolioprojectservice.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * *  Created by paulm on 1/24/2024
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectLoader implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    @Override
    public void run(String... args)  {
        if (projectRepository.count() == 0) {
            loadProjects();
        }
    }

    private void loadProjects() {
            Project project =Project.builder()
                            .projectName("First Test Project")
                            .description("First project to load")
                            .projectSource("https://www.github/accuPaul")
                            .projectUrl("https://www.google.com")
                            .tags(Arrays.asList("JAVA","SPRING"))
                    .build();

            Project project2 = Project.builder()
                    .projectName("Second Test Project")
                    .description("Second project to load")
                    .projectSource("https://www.github/accuPaul")
                    .projectUrl("https://www.google.com")
                    .tags(Arrays.asList("DOCKER","MYSQL"))
                    .build();

            Project project3 = Project.builder()
                    .projectName("Last Test Project")
                    .description("Last project to load")
                    .projectSource("https://www.github/accuPaul")
                    .projectUrl("https://www.bing.com")
                    .tags(Arrays.asList("Hibernate,REST"))
                    .build();

           projectRepository.save(project);
           projectRepository.save(project2);
           projectRepository.save(project3);
    }
}
