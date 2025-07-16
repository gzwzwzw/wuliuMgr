package com.rabbiter.lm.service.impl;

import com.rabbiter.lm.model.entity.Employee;
import com.rabbiter.lm.repository.EmployeeRepository;
import com.rabbiter.lm.service.EmployeeService;
import com.rabbiter.lm.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        employee.setCreateAt(DataTimeUtil.getNowTimeString());
        return employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employee.setUpdateAt(DataTimeUtil.getNowTimeString());
        employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
