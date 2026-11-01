package com.example.personservice.api.person;

import java.util.UUID;

public record PersonResponse(UUID id, String name) {
}
