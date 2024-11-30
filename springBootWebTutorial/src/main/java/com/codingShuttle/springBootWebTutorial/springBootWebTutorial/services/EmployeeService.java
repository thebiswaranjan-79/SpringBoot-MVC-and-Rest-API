package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.services;

import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.dto.EmployeeDTO;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.entities.EmployeeEntity;
import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public  EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);

        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
//
//        return  employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));

          return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return  employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);

        return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist){
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return  true;
    }

    public boolean isExistById(Long employeeId){
        return  employeeRepository.existsById(employeeId);
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String,Object> updates) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist){
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        // Reflextion
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

         return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
