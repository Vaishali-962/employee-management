package com.GenNext.employee_management.dto.requestDto;

import com.GenNext.employee_management.enums.Department;
import com.GenNext.employee_management.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;


@Data
public class EmployeeRequestDto {
    @NotBlank(message = "Name is Required!")
    private String name;


    @NotNull(message = "Designation is Required!")
    @Enumerated(EnumType.STRING)
    private Role designation;


    @NotNull(message = "Department is Required!")
    @Enumerated(EnumType.STRING)
    private Department department;


    @NotBlank(message = "Mobile Number is Required!")
    @Size(min = 10, max = 10, message = "Invalid Mobile Number")
    private String mobileNumber;


    @FutureOrPresent
    private LocalDate joinOn;
}
