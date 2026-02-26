package com.GenNext.employee_management.dto;


import com.GenNext.employee_management.enums.Department;
import com.GenNext.employee_management.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeResponseDto {
    private String name;
    @Enumerated(EnumType.STRING)
    private Role designation;
    @Enumerated(EnumType.STRING)
    private Department department;
    private BigDecimal salary;
    private String mobileNumber;
    private LocalDate joinOn;
}
