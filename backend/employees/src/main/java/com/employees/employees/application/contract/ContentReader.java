package com.employees.employees.application.contract;

import java.io.BufferedReader;
import java.util.List;
import java.util.function.Function;

import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.domain.EmployeeCollaboration;
import com.employees.employees.domain.WorkingPair;

public interface ContentReader {

    List<EmployeeCollaboration> read(MultipartFile file, Function<BufferedReader, List<EmployeeCollaboration>> processor);
}
