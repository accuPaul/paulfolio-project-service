package com.paulmount.paulfolioprojectservice.web.controller;

import com.paulmount.paulfolioprojectservice.services.ProjectService;
import com.paulmount.paulfolioprojectservice.web.model.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * *  Created by paulm on 2/13/2024
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<TagDto>> getTags() {
        return new ResponseEntity<>(projectService.getTagList(), HttpStatus.OK);
    }

    @GetMapping("{tagName}")
    public ResponseEntity<TagDto> getTagCount(@PathVariable("tagName") String tagName) {
        return new ResponseEntity<>(projectService.getTag(tagName),HttpStatus.OK);
    }
}
