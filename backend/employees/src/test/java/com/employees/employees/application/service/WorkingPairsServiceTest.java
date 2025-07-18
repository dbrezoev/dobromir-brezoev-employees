package com.employees.employees.application.service;

import static org.assertj.core.api.Assertions.tuple;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.employees.employees.domain.Employee;
import com.employees.employees.domain.Period;
import com.employees.employees.domain.WorkingPair;

class WorkingPairsServiceTest {

    private final WorkingPairsService workingPairsService = new WorkingPairsService();

    private final Long COMMON_PROJECT_ID = 2L;
    private final int YEAR_2025 = 2025;
    private final int MONTH_5 = 5;

    private final LocalDate start = LocalDate.of(YEAR_2025, MONTH_5, 11);
    private final LocalDate end = start.plusDays(10);
    private final Employee john = new Employee(1L, COMMON_PROJECT_ID, new Period(start, end));

    private final LocalDate start2 = LocalDate.of(YEAR_2025, MONTH_5, 1);
    private final LocalDate end2 = LocalDate.of(YEAR_2025, MONTH_5, 30);
    private final Employee henry = new Employee(COMMON_PROJECT_ID, 2L, new Period(start2, end2));

    private final LocalDate start3 = LocalDate.of(YEAR_2025, MONTH_5, 8);
    private final LocalDate end3 = LocalDate.of(YEAR_2025, MONTH_5, 20);
    private final Employee maria = new Employee(3L, COMMON_PROJECT_ID, new Period(start3, end3));

    @Test
    void extractWorkingPairs() {

        List<WorkingPair> result = workingPairsService.getWorkingPairs(List.of(john, henry, maria));

        Assertions.assertThat(result).hasSize(3);
        Assertions.assertThat(result)
                        .extracting(WorkingPair::firstEmployeeId, WorkingPair::secondEmployeeId, WorkingPair::days)
                                .containsExactly(
                                        tuple(henry.id(), maria.id(), 12),
                                        tuple(john.id(), henry.id(), 10),
                                        tuple(john.id(), maria.id(), 9)
                                );
    }


    @Test
    void extractWorkingPairsWithExternalEmployee() {

        var externalEmployee = new Employee(4L, 99L,
                new Period(LocalDate.of(YEAR_2025, MONTH_5, 11), LocalDate.of(YEAR_2025, MONTH_5, 30)));

        List<WorkingPair> result = workingPairsService.getWorkingPairs(List.of(john, henry, maria, externalEmployee));

        Assertions.assertThat(result).hasSize(3);
    }

    @Test
    void twoEmployeesWorkedTogetherOnTwoProjects() {

        Employee johnBefore = new Employee(john.id(), 10L,
                new Period(LocalDate.of(2012, 10, 11), LocalDate.of(2012, 10, 21)));

        Employee mariaBefore = new Employee(maria.id(), 10L,
                new Period(LocalDate.of(2012, 10, 1), LocalDate.of(2012, 10, 31)));

        List<WorkingPair> result = workingPairsService.getWorkingPairs(List.of(johnBefore, mariaBefore, john, henry, maria));

        Assertions.assertThat(result).hasSize(3);
        Assertions.assertThat(result)
                .extracting(WorkingPair::firstEmployeeId, WorkingPair::secondEmployeeId, WorkingPair::days)
                .containsExactly(
                        tuple(john.id(), maria.id(), 19),
                        tuple(henry.id(), maria.id(), 12),
                        tuple(john.id(), henry.id(), 10)
                );
    }
}
