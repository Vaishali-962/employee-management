package com.GenNext.employee_management.model;

import com.GenNext.employee_management.enums.Department;
import com.GenNext.employee_management.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name= "emp_name", nullable=false)
    private String name;

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="department", nullable=false)
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name="salary", nullable=false)
    private BigDecimal salary;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="joining_date")
    private LocalDate joiningDate;

}
