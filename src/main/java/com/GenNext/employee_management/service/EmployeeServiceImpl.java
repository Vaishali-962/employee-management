package com.GenNext.employee_management.service;

import com.GenNext.employee_management.model.Employee;
import com.GenNext.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    public Employee updateEmployeeById(Employee employee, Long id){
        employee.setId(id);
        Employee emp = employeeRepository.save(employee);
        return emp;
    }

    public Employee updateEmployeeSalary(Long empId, BigDecimal salary){
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if(optionalEmployee.isPresent()){
            Employee emp = optionalEmployee.get();
            emp.setSalary(salary);
            Employee employee = employeeRepository.save(emp);
            return employee;
        }else{
            throw new RuntimeException("Why he cut's night of our house only");
        }
    }
}
