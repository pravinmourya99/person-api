package com.example.personservice.domain.person;

import com.example.personservice.shared.exception.ResourceNotFoundException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Person create(String name) {
        return repository.save(new Person(UUID.randomUUID(), name));
    }

    @Transactional(readOnly = true)
    public Person getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found: " + id));
    }
}
