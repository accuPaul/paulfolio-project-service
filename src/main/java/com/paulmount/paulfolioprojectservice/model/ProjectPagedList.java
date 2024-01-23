package com.paulmount.paulfolioprojectservice.model;


import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProjectPagedList extends PageImpl<ProjectDto> {
    public ProjectPagedList(List<ProjectDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ProjectPagedList(List<ProjectDto> content) {
        super(content);
    }
}
