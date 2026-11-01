package com.example.personservice.api.person;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.personservice.domain.person.Person;
import com.example.personservice.domain.person.PersonService;
import com.example.personservice.shared.exception.GlobalExceptionHandler;
import com.example.personservice.shared.exception.ResourceNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PersonController.class)
@Import(GlobalExceptionHandler.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService service;

    @Test
    void createReturns201() throws Exception {
        when(service.create("Sample")).thenReturn(new Person(UUID.randomUUID(), "Sample"));

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sample"));
    }

    @Test
    void blankNameReturns400() throws Exception {
        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void missingReturns404() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.getById(id)).thenThrow(new ResourceNotFoundException("not found"));

        mockMvc.perform(get("/persons/" + id))
                .andExpect(status().isNotFound());
    }
}
