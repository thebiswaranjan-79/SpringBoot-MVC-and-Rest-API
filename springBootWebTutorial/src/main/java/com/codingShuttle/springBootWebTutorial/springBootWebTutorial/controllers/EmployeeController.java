package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.controllers;

import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.dto.EmployeeDTO;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.exceptions.ResourceNotFoundException;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        // return new EmployeeDTO(employeeId, "Biswa", "biswa@gmail.com", 22, LocalDate.of(2024, 1,2), true);
        // Jackson Convert My Java Object to the Json Object
        Optional<EmployeeDTO> employeeDTO  = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Emploee Not Found"));
    }




    @GetMapping //http://localhost:8080/employees?age=12&sortBy=anuj
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false)Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody  @Valid EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        EmployeeDTO savedEmployee =  employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return  ResponseEntity.ok(true);
        return  ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
