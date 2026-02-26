package com.GenNext.employee_management.controller;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.model.Employee;
import com.GenNext.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto){
       EmployeeResponseDto savedEmployee = employeeService.createEmployee(employeeRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /* READING DATA FROM SERVER */

    /* Read ALL DATA */
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(){
        List<EmployeeResponseDto> employeesList = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employeesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable(name="id") Long empId){
        EmployeeResponseDto employee = employeeService.getEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeById(@RequestBody Employee employee, @PathVariable(name="employee_id") Long empId){
          EmployeeResponseDto employeeResponseDto  = employeeService.updateEmployeeById(employee, empId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);
    }

    @PatchMapping("/{employeeId}/salary")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeSalary(@PathVariable(name="employeeId")Long empId, @RequestParam BigDecimal salary){
        EmployeeResponseDto employeeResponseDto = employeeService.updateEmployeeSalary(empId, salary);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDto);

    }

    @DeleteMapping("/{employee_id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable(name="employee_id") Long empId){
        employeeService.deleteEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
