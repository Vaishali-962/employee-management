package com.GenNext.employee_management.service;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.mapper.EmployeeMapper;
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
    private final EmployeeMapper employeeMapper;


    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee savedEmployee = employeeRepository.save(employeeMapper.toEntity(employeeRequestDto));
        return employeeMapper.toDto(savedEmployee);
    }

    public List<EmployeeResponseDto> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponseDto> empDtoList = employeeList.stream().map(employeeMapper::toDto).collect(Collectors.toList());
        return empDtoList;
    }

    public EmployeeResponseDto getEmployeeById(Long id){

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        EmployeeResponseDto employeeResponseDto = optionalEmployee
                .map(employeeMapper::toDto)
                .orElseThrow(()->new RuntimeException("Employee not found for given id " + id));

        return employeeResponseDto;
    }

    public EmployeeResponseDto updateEmployeeById(EmployeeRequestDto employeeRequestDto, Long id){
        employeeMapper.toEntity(employeeRequestDto).setId(id);
        Employee emp = employeeRepository.save(employeeMapper.toEntity(employeeRequestDto));
        return employeeMapper.toDto(emp);
    }

    public EmployeeResponseDto updateEmployeeSalary(Long empId, BigDecimal salary){
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if(optionalEmployee.isPresent()){
            Employee emp = optionalEmployee.get();
            emp.setSalary(salary);
            Employee employee = employeeRepository.save(emp);
            return employeeMapper.toDto(employee);
        }else{
            throw new RuntimeException("Employee Not Found for given id " + empId );
        }
    }

    public void deleteEmployeeById(Long empId){
        employeeRepository.deleteById(empId);
    }


}
