package com.GenNext.employee_management.service;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.model.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long Id);

    EmployeeResponseDto updateEmployeeById(Employee empId, Long id);

    EmployeeResponseDto updateEmployeeSalary(Long empId, BigDecimal salary);

    void deleteEmployeeById(Long empId);
}
