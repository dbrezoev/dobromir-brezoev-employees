package com.employees.employees.application;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.infrastructure.FileContentReader;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Validated
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final FileContentReader contentReader;

    @GetMapping
    public ResponseEntity<?> test() {
        System.out.println("test");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        contentReader.read(file);
        return ResponseEntity.ok().build();
    }
}
