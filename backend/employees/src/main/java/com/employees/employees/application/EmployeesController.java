package com.employees.employees.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.domain.WorkingPair;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Validated
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesFacade facade;

    @GetMapping
    public ResponseEntity<?> test() {
        System.out.println("test");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<List<WorkingPair>> upload(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        return ResponseEntity.ok(facade.calculateWorkingPairs(file));
    }
}
