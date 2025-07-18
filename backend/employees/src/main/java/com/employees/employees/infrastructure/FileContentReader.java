package com.employees.employees.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.application.contract.ContentReader;
import com.employees.employees.domain.EmployeeCollaboration;
import com.employees.employees.domain.WorkingPair;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileContentReader implements ContentReader {

    public List<EmployeeCollaboration> read(MultipartFile file, Function<BufferedReader, List<EmployeeCollaboration>> processor) {
        try (BufferedReader csvFileReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return processor.apply(csvFileReader);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file", e);
        }
    }
}
