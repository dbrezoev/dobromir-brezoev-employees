package com.employees.employees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeesApplicationTests {

	@Test
	void contextLoads() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
		LocalDate startDate = LocalDate.parse("07-17-25", formatter);

		System.out.println(startDate);
	}

}
