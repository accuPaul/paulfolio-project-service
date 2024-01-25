package com.paulmount.paulfolioprojectservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("kebab")
@JsonTest
class ProjectDtoTest extends BaseTestProject {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializer() throws JsonProcessingException {
        ProjectDto projectDto = getDto();

        String jsonString = objectMapper.writeValueAsString(projectDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserializer() throws JsonProcessingException {
        String json = "{\"id\":\"8ffbea35-a3a5-4a83-9e04-65d655cef26b\",\"version\":0,\"created-date\":\"2024-01-25T16:30:59-0500\",\"last-modified-date\":\"2024-01-25T16:30:59-0500\",\"project-name\":\"Test Project\",\"description\":\"Sample POJO for testing\",\"project-url\":\"https://www.exmaple.com\",\"project-source\":\"https://www.github.com\",\"tags\":[\"TAG1\",\"TAG2\",\"TAG3\"]}";

        ProjectDto dto = objectMapper.readValue(json, ProjectDto.class);

        System.out.println(dto);

    }

}