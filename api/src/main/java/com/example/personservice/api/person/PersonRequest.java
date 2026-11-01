package com.example.personservice.api.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonRequest(@NotBlank @Size(max = 255) String name) {
}
