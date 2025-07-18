package com.employees.employees.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.application.validation.SupportedDateFormats;
import com.employees.employees.domain.EmployeeCollaboration;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Validated
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesFacade facade;

    @PostMapping
    public ResponseEntity<List<EmployeeCollaboration>> upload(@RequestParam MultipartFile file,
                                                              @RequestParam(defaultValue = "yyyy-MM-dd") String dateFormat) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!SupportedDateFormats.isSupported(dateFormat)) {
            throw new IllegalArgumentException("Invalid date format");
        }

        return ResponseEntity.ok(facade.calculateWorkingPairs(file, dateFormat));
    }
}
