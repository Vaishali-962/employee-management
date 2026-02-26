package com.GenNext.employee_management.service;

import com.GenNext.employee_management.model.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long Id);

    Employee updateEmployeeById(Employee empId, Long id);

    Employee updateEmployeeSalary(Long empId, BigDecimal salary);
}
