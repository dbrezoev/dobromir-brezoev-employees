package com.employees.employees.infrastructure;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.employees.employees.domain.EmployeeCollaboration;

@ExtendWith(MockitoExtension.class)
class FileContentReaderTest {

    private final FileContentReader fileContentReader = new FileContentReader();
    private final Function<BufferedReader, List<EmployeeCollaboration>> noop = reader -> List.of();

    @Test
    void readNotExistingFileShouldThrow() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException());

        assertThrows(IllegalArgumentException.class, () -> fileContentReader.read(file, noop));
    }
}
