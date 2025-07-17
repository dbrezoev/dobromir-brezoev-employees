package com.employees.employees.application.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.employees.employees.application.parser.CsvRecord;
import com.employees.employees.domain.Employee;
import com.employees.employees.domain.Period;

@Component
public class EmployeeMapper {

    public List<Employee> fromCsvRecords(List<CsvRecord> csvRecords) {
        return csvRecords
            .stream()
            .map(r -> {
                var startDate = LocalDate.parse(r.getDateFrom().trim());
                var endDate = Optional.ofNullable(r.getDateTo())
                        .map(d -> LocalDate.parse(r.getDateFrom().trim()))
                        .orElse(LocalDate.now());
               return new Employee(r.getEmployeeId(), r.getProjectId(), new Period(startDate, endDate)) ;
            })
            .toList();
    }
}
