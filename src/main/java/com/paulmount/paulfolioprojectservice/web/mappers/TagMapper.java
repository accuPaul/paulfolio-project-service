package com.paulmount.paulfolioprojectservice.web.mappers;/* Created by paulm on 2/13/2024*/

import com.paulmount.paulfolioprojectservice.domain.Tag;
import com.paulmount.paulfolioprojectservice.web.model.TagDto;
import org.mapstruct.Mapper;

@Mapper
public interface TagMapper {

    TagDto projectionToTagDto(TagProjection tagProjection);

    TagDto tagToTagDto(Tag tag);

    Tag tagDtoToTag(TagDto tagDto);
}
