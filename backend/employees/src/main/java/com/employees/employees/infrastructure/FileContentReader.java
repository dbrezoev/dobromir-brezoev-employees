package com.employees.employees.infrastructure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.application.config.CsvConfiguration;
import com.employees.employees.application.contract.ContentReader;
import com.employees.employees.application.mapper.EmployeeMapper;
import com.employees.employees.application.parser.CsvParser;
import com.employees.employees.application.parser.CsvRecord;
import com.employees.employees.domain.Employee;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileContentReader implements ContentReader {

    private final CsvConfiguration csvConfig;
    private final EmployeeMapper employeeMapper;
    private final CsvParser csvParser;

    public void read(MultipartFile file) {

        try (BufferedReader csvFileReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            List<CsvRecord> csvRecords = csvParser.parse(csvFileReader, csvConfig);

            List<Employee> employees = employeeMapper.fromCsvRecords(csvRecords);

            System.out.println(employees.size());
            //TODO: find longest working together pair

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File Not Found", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file", e);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
