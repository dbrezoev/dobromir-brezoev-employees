package com.employees.employees.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class CsvConfiguration {

    @Value("${csv.has-header-row}")
    private boolean hasHeaderRow;

    @Value("${csv.separator}")
    private char separator;
}
