package com.employees.employees.application.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.employees.employees.application.parser.CsvRecord;
import com.employees.employees.domain.Employee;
import com.employees.employees.domain.Period;

@Component
public class EmployeeMapper {

    public List<Employee> fromCsvRecords(List<CsvRecord> csvRecords, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        return csvRecords
            .stream()
            .map(r -> {
                try {
                    var startDate = LocalDate.parse(r.getDateFrom().trim(), formatter);
                    var endDate = Optional.ofNullable(r.getDateTo())
                            .map(String::trim)
                            .filter(s -> !s.equalsIgnoreCase("NULL") && !s.isEmpty())
                            .map(s -> LocalDate.parse(s, formatter))
                            .orElse(LocalDate.now());
                   return new Employee(r.getEmployeeId(), r.getProjectId(), new Period(startDate, endDate)) ;

                } catch (DateTimeParseException e) {
                    var message = String.format("Invalid date format in record with emplId: %d and projectId: %d. Expected format: %s",
                            r.getEmployeeId(), r.getProjectId(), dateFormat);
                    throw new IllegalArgumentException(message, e);
                }
            })
            .toList();
    }
}
