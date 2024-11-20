package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.controllers;

import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController // It is use for Rest API character
@RequestMapping(path = "/employees") // It is the Parent Mapping
public class EmployeeController {
//    @GetMapping(path = "/getSecretMsg")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : Hey Biswaranjan YOU Can Do IT";
//    }
    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Biswa", "biswa@gmail.com", 22, LocalDate.of(2024, 1,2), true);
        // Jackson Convert My Java Object to the Json Object
    }
    @GetMapping //http://localhost:8080/employees?age=12&sortBy=anuj
    public String getAllEmployees(@RequestParam(required = false)Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi age "+ age + " "+sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Updated the Employee Details";
    }

}
