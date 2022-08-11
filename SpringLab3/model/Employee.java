package com.example.SpringLab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employee {
    @NotNull(message = "id must be not null")
    @Range(min=2, message = "id length must be more than 2")
    private int id;
    @NotEmpty(message = "name must be not empty")
    @Size(min=4, message = "name size must be more than 4")
    private String name;
    @NotNull(message = "age should not be null")
    @Range(min=25,message = "age should be more than 25")
    private int age;
    @Pattern(regexp = "^(false)$", message = "on leave should be false")
    private String onLeave;
    @NotNull(message = "employee year should not be null")
    @Range(min=1900,max=2022,message = "not valid year")
    private int employmentYear;
    @NotNull(message = "annual leave should not be null")
    @Range(min=0,max=30)
    private int annualLeave;
/*
must be a valid year

 */
}
