package com.example.personservice.shared.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void formatsAsIsoDate() {
        assertThat(DateUtils.toIsoDate(LocalDate.of(2026, 1, 31))).isEqualTo("2026-01-31");
    }
}
