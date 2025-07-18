package com.employees.employees.application.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.employees.employees.domain.Employee;
import com.employees.employees.domain.EmployeeCollaboration;
import com.employees.employees.domain.Period;
import com.employees.employees.domain.WorkingPair;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkingPairsService {

    public List<EmployeeCollaboration> getWorkingPairs(List<Employee> employees) {
        Map<Long, List<Employee>> groupedByProject = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::projectId));

        List<WorkingPair> workingPairs = new ArrayList<>();

        groupedByProject.forEach((projectId, projectEmployees) -> {
            for (int i = 0; i < projectEmployees.size(); i++) {
                Employee e1 = projectEmployees.get(i);

                for (int j = i + 1; j < projectEmployees.size(); j++) {
                    Employee e2 = projectEmployees.get(j);

                    int overlapDays = calculateOverlappingDays(e1.period(), e2.period());

                    if (overlapDays > 0) {
                        Long firstEmpId = Math.min(e1.id(), e2.id());
                        Long secondEmpId = Math.max(e1.id(), e2.id());
                        workingPairs.add(new WorkingPair(firstEmpId, secondEmpId, overlapDays, projectId));
                    }
                }
            }
        });

        Map<EmployeesPair, List<WorkingPair>> grouped = workingPairs.stream()
                .collect(Collectors.groupingBy(
                        wp -> new EmployeesPair(wp.firstEmployeeId(), wp.secondEmployeeId())
                ));

        List<EmployeeCollaboration> result = grouped.entrySet().stream()
                .map(entry -> {
                    EmployeesPair pair = entry.getKey();
                    List<WorkingPair> pairs = entry.getValue();

                    int totalDays = pairs.stream().mapToInt(WorkingPair::days).sum();
                    List<Long> projectIds = pairs.stream().map(WorkingPair::projectId).distinct().toList();

                    return new EmployeeCollaboration(pair.firstEmployeeId(), pair.secondEmployeeId(), totalDays, projectIds);
                })
                .sorted(Comparator.comparingInt(EmployeeCollaboration::totalDays).reversed())
                .toList();

        return result;

//        Map<EmployeesPair, Integer> mergedSameEmployees = workingPairs
//            .stream()
//            .collect(Collectors.toMap(
//                    wp -> new EmployeesPair(wp.firstEmployeeId(), wp.secondEmployeeId()),
//                    WorkingPair::days,
//                    Integer::sum
//            ));
//
//        return mergedSameEmployees
//                .entrySet()
//                .stream()
//                .map(e -> {
//                    long firstEmployeeId = e.getKey().firstEmployeeId();
//                    long secondEmployeeId = e.getKey().secondEmployeeId();
//                    return new WorkingPair(Math.min(firstEmployeeId, secondEmployeeId), Math.max(firstEmployeeId, secondEmployeeId), e.getValue());
//                })
//                .sorted(Comparator.comparingInt(WorkingPair::days).reversed())
//                .toList();
    }

    private record EmployeesPair(long firstEmployeeId,  long secondEmployeeId) {}

    public int calculateOverlappingDays(Period firstPeriod, Period secondPeriod) {
        var start = firstPeriod.startDate().isAfter(secondPeriod.startDate()) ? firstPeriod.startDate() : secondPeriod.startDate();
        var end = firstPeriod.endDate().isBefore(secondPeriod.endDate()) ? firstPeriod.endDate() : secondPeriod.endDate();

        if (start.isAfter(end)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(start, end);
    }
}
