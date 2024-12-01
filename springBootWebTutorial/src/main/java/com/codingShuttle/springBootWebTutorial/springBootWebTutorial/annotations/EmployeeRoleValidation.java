package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {

    String message() default "ROLE OF THE EMPLOYEE CAN BE ADMIN OR USER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
