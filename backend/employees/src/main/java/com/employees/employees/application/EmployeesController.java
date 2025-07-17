package com.employees.employees.application;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping(value = "/employees")
@RequiredArgsConstructor
public class EmployeesController {

    @GetMapping
    public ResponseEntity<?> test() {
        System.out.println("test");
        return ResponseEntity.ok().build();
    }
}
