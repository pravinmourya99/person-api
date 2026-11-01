package com.example.personservice.domain.person;

import java.util.Optional;
import java.util.UUID;

/** Port for database access, implemented in the persistence module. */
public interface PersonRepository {

    Person save(Person person);

    Optional<Person> findById(UUID id);
}
