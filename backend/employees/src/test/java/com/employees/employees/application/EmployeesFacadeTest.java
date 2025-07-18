package com.employees.employees.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.employees.employees.domain.EmployeeCollaboration;
import com.employees.employees.domain.WorkingPair;

@SpringBootTest
class EmployeesFacadeTest {

    @Autowired
    private EmployeesFacade employeesFacade;

    @Test
    void calculateWorkingPairs() throws IOException {
        MockMultipartFile mockMultipartFile = prepareFile("src/test/resources/employees1.csv");

        var workingPairs = employeesFacade.calculateWorkingPairs(mockMultipartFile, "yyyy-MM-dd");

        assertThat(workingPairs)
                .extracting(EmployeeCollaboration::firstEmployeeId, EmployeeCollaboration::secondEmployeeId, EmployeeCollaboration::totalDays)
                .containsExactly(
                        tuple(143L, 218L, 4)
                );
    }

    @Test
    void calculateWorkingPairsWhenNoOverlap() throws IOException {
        MockMultipartFile mockMultipartFile = prepareFile("src/test/resources/no-overlap.csv");

        var workingPairs = employeesFacade.calculateWorkingPairs(mockMultipartFile, "yyyy-MM-dd");

        assertThat(workingPairs).hasSize(0);
    }

    private static MockMultipartFile prepareFile(String pathname) throws IOException {
        File file = new File(pathname);
        return new MockMultipartFile(
                "file",
                file.getName(),
                "text/csv",
                new FileInputStream(file)
        );
    }
}
