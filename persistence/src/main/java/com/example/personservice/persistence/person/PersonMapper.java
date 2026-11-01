package com.example.personservice.persistence.person;

import com.example.personservice.domain.person.Person;
import java.util.UUID;

final class PersonMapper {

    private PersonMapper() {
    }

    static PersonEntity toEntity(Person person) {
        return new PersonEntity(person.id().toString(), person.name());
    }

    static Person toDomain(PersonEntity entity) {
        return new Person(UUID.fromString(entity.getId()), entity.getName());
    }
}
