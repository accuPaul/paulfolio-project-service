package com.paulmount.paulfolioprojectservice.web.model;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 * *  Created by paulm on 1/25/2024
 */

public class BaseTestProject {
    ProjectDto getDto() {
        return ProjectDto.builder()
                .id(UUID.randomUUID())
                .projectName("Test Project")
                .description("Sample POJO for testing")
                .projectUrl("https://www.exmaple.com")
                .projectSource("https://www.github.com")
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .version(0)
                .tags(Arrays.asList("TAG1", "TAG2","TAG3"))
                .build();
    }
}
