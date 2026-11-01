package com.example.personservice.api.person;

import com.example.personservice.domain.person.Person;
import com.example.personservice.domain.person.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@Tag(name = "Person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a person")
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest request) {
        Person created = service.create(request.name());
        return ResponseEntity.created(URI.create("/persons/" + created.id()))
                .body(PersonApiMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a person by id")
    public PersonResponse getById(@PathVariable UUID id) {
        return PersonApiMapper.toResponse(service.getById(id));
    }
}
