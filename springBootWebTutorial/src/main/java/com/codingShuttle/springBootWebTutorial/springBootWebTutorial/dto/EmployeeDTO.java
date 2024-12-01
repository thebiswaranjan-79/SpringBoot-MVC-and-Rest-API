package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;
    @NotEmpty(message = "Name Can not be Empty")
    private String name;
    private String email;
    private Integer age;

    @NotBlank(message = "The Role of the employee Can not be Blank ")
    @Pattern(regexp = "^(ADMIN | USER)$")
    private String role; // ADMIN , USER

    @Digits(integer = 6, fraction = 2, message = "The Salary can be the form of xxxx.xx")
    private Double salary;

    @PastOrPresent(message = "The Date should be in Past or Present")
    private LocalDate dateofJoining;

    @JsonProperty("isActive")
    private Boolean isActive;


}
