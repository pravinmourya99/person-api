package com.example.personservice.persistence.person;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.personservice.persistence.TestContainersConfig;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainersConfig.class)
@ActiveProfiles("test")
class PersonJpaRepositoryTest {

    @Autowired
    private PersonJpaRepository repository;

    @Test
    void savesAndLoadsAgainstTheMigratedSchema() {
        String id = UUID.randomUUID().toString();

        repository.saveAndFlush(new PersonEntity(id, "Sample"));

        assertThat(repository.findById(id)).hasValueSatisfying(
                entity -> assertThat(entity.getName()).isEqualTo("Sample"));
    }
}
