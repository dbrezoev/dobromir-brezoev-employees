package com.employees.employees.application.contract;

import org.springframework.web.multipart.MultipartFile;

public interface ContentReader {

    void read(MultipartFile file);
}
