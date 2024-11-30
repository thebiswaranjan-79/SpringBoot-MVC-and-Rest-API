package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.controllers;

import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.dto.EmployeeDTO;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.entities.EmployeeEntity;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.repositories.EmployeeRepository;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController // It is use for Rest API character
@RequestMapping(path = "/employees") // It is the Parent Mapping

public class EmployeeController {
//    @GetMapping(path = "/getSecretMsg")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : Hey Biswaranjan YOU Can Do IT";
//    }

    private final EmployeeService employeeService;

    public  EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        // return new EmployeeDTO(employeeId, "Biswa", "biswa@gmail.com", 22, LocalDate.of(2024, 1,2), true);
        // Jackson Convert My Java Object to the Json Object
        return  employeeService.getEmployeeById(id);

    }
    @GetMapping //http://localhost:8080/employees?age=12&sortBy=anuj
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false)Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId, employeeDTO);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById( @PathVariable Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId, updates);
    }
}
