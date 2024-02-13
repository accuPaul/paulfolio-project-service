package com.paulmount.paulfolioprojectservice.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * *  Created by paulm on 2/13/2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {

    @NotBlank
    private String tagName;
    private Integer count;
}
