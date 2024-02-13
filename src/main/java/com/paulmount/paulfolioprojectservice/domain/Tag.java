package com.paulmount.paulfolioprojectservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * *  Created by paulm on 2/13/2024
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag {
    private String tagName;
    private Integer count;
}
