package com.employees.employees.application.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.employees.employees.domain.Employee;
import com.employees.employees.domain.Period;
import com.employees.employees.domain.WorkingPair;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkingPairsService {

    public List<WorkingPair> getWorkingPairs(List<Employee> employees) {
        Map<Long, List<Employee>> groupedByProject = employees.stream()
                .collect(Collectors.groupingBy(Employee::projectId));

        List<WorkingPair> workingPairs = new ArrayList<>();
        groupedByProject.forEach((projectId, projectEmployees) -> {
            for (int i = 0; i < projectEmployees.size(); i++) {
                Employee e1 = projectEmployees.get(i);

                for (int j = i + 1; j < projectEmployees.size(); j++) {
                    Employee e2 = projectEmployees.get(j);

                    int overlapDays = calculateOverlappingDays(e1.period(), e2.period());

                    if (overlapDays > 0) {
                        workingPairs.add(new WorkingPair(e1.id(), e2.id(), overlapDays));
                    }
                }
            }
        });

        return workingPairs.stream()
                .sorted(Comparator.comparingInt(WorkingPair::days).reversed())
                .toList();
    }

    public int calculateOverlappingDays(Period period1, Period period2) {
        var start = period1.startDate().isAfter(period2.startDate()) ? period1.startDate() : period2.startDate();
        var end = period1.endDate().isBefore(period2.endDate()) ? period1.endDate() : period2.endDate();

        if (start.isAfter(end)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(start, end);
    }


}
