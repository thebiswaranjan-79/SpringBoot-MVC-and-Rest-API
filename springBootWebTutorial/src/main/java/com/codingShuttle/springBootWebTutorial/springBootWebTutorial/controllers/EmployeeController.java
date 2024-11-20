package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.controllers;

import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.dto.EmployeeDTO;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.entities.EmployeeEntity;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // It is use for Rest API character
@RequestMapping(path = "/employees") // It is the Parent Mapping
public class EmployeeController {
//    @GetMapping(path = "/getSecretMsg")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : Hey Biswaranjan YOU Can Do IT";
//    }

    private final EmployeeRepository employeeRepository;

    public  EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        // return new EmployeeDTO(employeeId, "Biswa", "biswa@gmail.com", 22, LocalDate.of(2024, 1,2), true);
        // Jackson Convert My Java Object to the Json Object
        return  employeeRepository.findById(id).orElse(null);

    }
    @GetMapping //http://localhost:8080/employees?age=12&sortBy=anuj
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false)Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Updated the Employee Details";
    }

}
