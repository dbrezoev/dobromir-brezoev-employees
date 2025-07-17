package com.employees.employees.application.parser;

import com.opencsv.bean.CsvBindByPosition;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CsvRecord {
    @CsvBindByPosition(position = 0)
    private Long employeeId;

    @CsvBindByPosition(position = 1)
    private Long projectId;

    @CsvBindByPosition(position = 2)
    private String dateFrom;

    @CsvBindByPosition(position = 3)
    private String dateTo;
}
