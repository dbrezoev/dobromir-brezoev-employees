package com.employees.employees.domain;

import java.util.List;

public record EmployeeCollaboration(long firstEmployeeId, long secondEmployeeId, int totalDays, List<Long> projectIds) {
}
