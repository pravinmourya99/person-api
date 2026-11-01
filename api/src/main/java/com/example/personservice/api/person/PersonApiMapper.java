package com.example.personservice.api.person;

import com.example.personservice.domain.person.Person;

final class PersonApiMapper {

    private PersonApiMapper() {
    }

    static PersonResponse toResponse(Person person) {
        return new PersonResponse(person.id(), person.name());
    }
}
