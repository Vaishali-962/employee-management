package com.GenNext.employee_management.service;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.model.Employee;
import com.GenNext.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee savedEmployee = employeeRepository.save(toEmployeeEntity(employeeRequestDto));
        return toDto(savedEmployee);
    }

    public List<EmployeeResponseDto> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponseDto> empDtoList = employeeList.stream().map(this::toDto).collect(Collectors.toList());
        return empDtoList;
    }

    public EmployeeResponseDto getEmployeeById(Long id){

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        EmployeeResponseDto employeeResponseDto = optionalEmployee.map(this::toDto).orElse(null);
        return employeeResponseDto;
    }

    public EmployeeResponseDto updateEmployeeById(Employee employee, Long id){
        employee.setId(id);
        Employee emp = employeeRepository.save(employee);
        return toDto(emp);
    }

    public EmployeeResponseDto updateEmployeeSalary(Long empId, BigDecimal salary){
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if(optionalEmployee.isPresent()){
            Employee emp = optionalEmployee.get();
            emp.setSalary(salary);
            Employee employee = employeeRepository.save(emp);
            return toDto(employee);
        }else{
            throw new RuntimeException("Why he cut's night of our house only");
        }
    }

    public void deleteEmployeeById(Long empId){
        employeeRepository.deleteById(empId);
    }

    /* Employee Response DTO (from backend to client) */

    EmployeeResponseDto toDto(Employee employee){

        return EmployeeResponseDto
                .builder()
                .name(employee.getName())
                .designation(employee.getRole())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .mobileNumber(employee.getMobileNumber())
                .joinOn(employee.getJoiningDate())
                .build();
    }

    /* Employee Request DTO (From Client to Backend) */
    Employee toEmployeeEntity(EmployeeRequestDto employeeRequestDto){
        Employee employee = new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setRole(employeeRequestDto.getDesignation());
        employee.setDepartment(employeeRequestDto.getDepartment());
        employee.setSalary(new BigDecimal("0.00"));
        employee.setMobileNumber(employeeRequestDto.getMobileNumber());
        employee.setJoiningDate(employeeRequestDto.getJoinOn());
        return employee;


    }
}
