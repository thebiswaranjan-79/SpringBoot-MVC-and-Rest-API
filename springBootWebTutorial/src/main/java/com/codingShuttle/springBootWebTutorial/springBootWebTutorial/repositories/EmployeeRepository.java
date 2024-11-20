package com.codingShuttle.springBootWebTutorial.springBootWebTutorial.repositories;

import com.codingShuttle.springBootWebTutorial.springBootWebTutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {



}
