package com.paulmount.paulfolioprojectservice.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    @Null
    private UUID id;
    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String projectName;
    @NotBlank
    private String description;
    @URL
    private String projectUrl;
    @URL
    private String projectSource;

    private List<String> tags;
}
