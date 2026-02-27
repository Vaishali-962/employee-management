package com.GenNext.employee_management.mapper;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    @Mapping(target = "designation", source = "role")
    @Mapping(target = "joinOn", source = "joiningDate")
    EmployeeResponseDto toDto(Employee employee);


    @Mapping(target = "role", source = "designation")
    @Mapping(target = "joiningDate", source = "joinOn")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);


    List<EmployeeResponseDto> toDtoList(List<Employee> employeeList);

}
