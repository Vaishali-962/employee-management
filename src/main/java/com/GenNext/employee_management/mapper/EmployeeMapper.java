package com.GenNext.employee_management.mapper;

import com.GenNext.employee_management.dto.requestDto.EmployeeRequestDto;
import com.GenNext.employee_management.dto.responseDto.EmployeeResponseDto;
import com.GenNext.employee_management.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {
    @Mapping(target = "name", qualifiedByName = "toUppercase")
    @Mapping(target = "salary", qualifiedByName = "handleNullSalary")
    @Mapping(target = "mobileNumber", qualifiedByName = "maskedNumber")
    @Mapping(target = "designation", source = "role")
    @Mapping(target = "joinOn", source = "joiningDate", dateFormat = "dd-MM-yy")
    EmployeeResponseDto toDto(Employee employee);



    @Mapping(target = "role", source = "designation")
    @Mapping(target = "joiningDate", source = "joinOn")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);


    List<EmployeeResponseDto> toDtoList(List<Employee> employeeList);


    @Named("toUppercase")
    // for converting the  names to uppercase
    default String getEmployeeNameModified(String name) {
        if (name == null) {
            return "N/A";
        }

        return name.toUpperCase();
    }


    @Named("handleNullSalary")
    // for null salary
    default BigDecimal getDefaultSalaryForNullSalary(BigDecimal salary){
        return salary != null ? salary : BigDecimal.ZERO;
    }

    @Named("maskedNumber")
    default String getMaskedMobileNumber(String mobileNumber){
        if (mobileNumber == null){
            return "N/A";
        }
        StringBuilder sb =  mobileNumber
                .substring(0, 6)
                .chars()
                .mapToObj(e -> (char) e)
                .map(e -> 'X')
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append);

        sb.append(mobileNumber.substring(6));
        return String.valueOf(sb);
    }

//    @Named("dateFormater")
//    default String getLocalDateFormatted(LocalDate localDate){
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//        return localDate.format(dateTimeFormatter);
////        String formatedDateStr = localDate.format(dateTimeFormatter);
//
////        return LocalDate.parse(formatedDateStr);
//    }

}
