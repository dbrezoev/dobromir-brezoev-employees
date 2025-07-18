package com.employees.employees.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.employees.employees.domain.Period;

public class OverlappingDaysTest {

    private final WorkingPairsService workingPairsService = new WorkingPairsService();
    private final int YEAR_2025 = 2025;
    private final int MONTH_6 = 6;
    private final int MONTH_5 = 5;
    private final int MONTH_4 = 4;

    @Test
    void calculateOverlappingDaysForTheSamePeriod() {
        var start = LocalDate.of(YEAR_2025, MONTH_5, 11);
        var end = start.plusDays(3);

        var period1 = new Period(start, end);
        var period2 = new Period(start, end);

        int overlappingDays = workingPairsService.calculateOverlappingDays(period1, period2);
        assertThat(overlappingDays).isEqualTo(3L);
    }

    @Test
    void calculateOverlappingDaysFor17Days() {
        var period1 = new Period(LocalDate.of(YEAR_2025, MONTH_5, 11), LocalDate.of(YEAR_2025, MONTH_5, 17));
        var period2 = new Period(LocalDate.of(YEAR_2025, MONTH_4, 10), LocalDate.of(YEAR_2025, MONTH_6, 11));

        int overlappingDays = workingPairsService.calculateOverlappingDays(period1, period2);
        assertThat(overlappingDays).isEqualTo(6L);
    }

    @Test
    void calculateOverlappingDaysForOneDay() {
        var period1 = new Period(LocalDate.of(YEAR_2025, MONTH_5, 11), LocalDate.of(YEAR_2025, MONTH_5, 17));
        var period2 = new Period(LocalDate.of(YEAR_2025, MONTH_5, 8), LocalDate.of(YEAR_2025, MONTH_5, 12));

        int overlappingDays = workingPairsService.calculateOverlappingDays(period1, period2);
        assertThat(overlappingDays).isEqualTo(1L);
    }
}
