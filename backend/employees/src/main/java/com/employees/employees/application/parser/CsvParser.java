package com.employees.employees.application.parser;

import java.io.BufferedReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employees.employees.application.config.CsvConfiguration;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvParser {

    public List<CsvRecord> parse(BufferedReader csvFileReader, CsvConfiguration csvConfig) {
        CsvToBeanBuilder<CsvRecord> csvToBeanBuilder = new CsvToBeanBuilder<>(csvFileReader);

        return csvToBeanBuilder
                .withSkipLines(csvConfig.isHasHeaderRow() ? 1 : 0)
                .withSeparator(csvConfig.getSeparator())
                .withType(CsvRecord.class)
                .build()
                .parse();
    }
}
