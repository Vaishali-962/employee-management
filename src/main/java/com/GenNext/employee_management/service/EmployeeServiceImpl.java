package com.GenNext.employee_management.service;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.exception.EmployeeException;
import com.GenNext.employee_management.mapper.EmployeeMapper;
import com.GenNext.employee_management.model.Employee;
import com.GenNext.employee_management.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        List<EmployeeResponseDto> empDtoList = employeeMapper.toDtoList(employeeList);
        return empDtoList;
    }

    public EmployeeResponseDto getEmployeeById(Long empId){

        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        EmployeeResponseDto employeeResponseDto = optionalEmployee
                .map(employeeMapper::toDto)
                .orElseThrow(()->new EmployeeException("EMPLOYEE_NOT_FOUND", "Employee not found for given id " + empId));

        return employeeResponseDto;
    }

    public EmployeeResponseDto updateEmployeeById(EmployeeRequestDto employeeRequestDto, Long id){
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->new EmployeeException("EMPLOYEE_NOT_FOUND", "Employee not found for id : " + id));
        Employee updatingEmployee = employeeMapper.toEntity(employeeRequestDto);
        updatingEmployee.setId(id);
        Employee savedEmployee = employeeRepository.save(updatingEmployee);

        return employeeMapper.toDto(savedEmployee);
    }

    @Transactional
    public EmployeeResponseDto updateEmployeeSalary(Long empId, BigDecimal salary){
        Employee existingEmployee = employeeRepository
                .findById(empId)
                .orElseThrow(()->new EmployeeException("EMPLOYEE_NOT_FOUND",
                        "Employee not found for id: " + empId));

        existingEmployee.setSalary(salary);
        return employeeMapper.toDto(existingEmployee);
    }

    public void deleteEmployeeById(Long empId){
        if(!employeeRepository.existsById(empId)){
            throw new EmployeeException("EMPLOYEE_NOT_FOUND", "Employee not found for id: " + empId);
        }
        employeeRepository.deleteById(empId);
    }


}
