package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    void update(Employee employee);

    void delete(String id);

    Employee findById(String id);

    List<Employee> findAll();

}
