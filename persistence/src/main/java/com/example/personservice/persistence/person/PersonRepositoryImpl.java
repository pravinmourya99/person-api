package com.example.personservice.persistence.person;

import com.example.personservice.domain.person.Person;
import com.example.personservice.domain.person.PersonRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonJpaRepository jpaRepository;

    public PersonRepositoryImpl(PersonJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Person save(Person person) {
        return PersonMapper.toDomain(jpaRepository.save(PersonMapper.toEntity(person)));
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return jpaRepository.findById(id.toString()).map(PersonMapper::toDomain);
    }
}
