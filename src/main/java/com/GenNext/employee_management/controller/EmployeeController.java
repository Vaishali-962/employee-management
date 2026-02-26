package com.GenNext.employee_management.controller;

import com.GenNext.employee_management.model.Employee;
import com.GenNext.employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    /* CREATE DATA ON SERVER */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
       Employee savedEmployee = employeeService.createEmployee(employee);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /* READING DATA FROM SERVER */

    /* Read ALL DATA */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeesList = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name="id") Long empId){
        Employee employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable(name="employee_id") Long empId){
        Employee employee1 = employeeService.updateEmployeeById(employee, empId);
        return ResponseEntity.status(HttpStatus.OK).body(employee1);
    }

    @PatchMapping("/{employeeId}/salary")
    public ResponseEntity<Employee> updateEmployeeSalary(@PathVariable(name="employeeId")Long empId, @RequestParam BigDecimal salary){
        Employee employee = employeeService.updateEmployeeSalary(empId, salary);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @DeleteMapping("/{employee_id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable(name="employee_id") Long empId){
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
