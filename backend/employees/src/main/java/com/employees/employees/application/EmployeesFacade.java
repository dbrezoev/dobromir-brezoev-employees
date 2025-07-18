package com.employees.employees.application;

import java.io.BufferedReader;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.application.config.CsvConfiguration;
import com.employees.employees.application.mapper.EmployeeMapper;
import com.employees.employees.application.parser.CsvParser;
import com.employees.employees.application.parser.CsvRecord;
import com.employees.employees.application.service.WorkingPairsService;
import com.employees.employees.domain.Employee;
import com.employees.employees.domain.WorkingPair;
import com.employees.employees.infrastructure.FileContentReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeesFacade {
    private final FileContentReader contentReader;
    private final CsvParser csvParser;
    private final CsvConfiguration csvConfig;
    private final EmployeeMapper employeeMapper;
    private final WorkingPairsService workingPairsService;

    public List<WorkingPair> calculateWorkingPairs(MultipartFile file) {
        return contentReader.read(file, this::getWorkingPairs);
    }

    private List<WorkingPair> getWorkingPairs(BufferedReader reader) {
        List<CsvRecord> csvRecords = csvParser.parse(reader, csvConfig);
        List<Employee> employees = employeeMapper.fromCsvRecords(csvRecords);
        return workingPairsService.getWorkingPairs(employees);
    }

}
