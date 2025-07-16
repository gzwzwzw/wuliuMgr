package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Driver;

import java.util.List;

public interface DriverService {

    Driver save(Driver driver);

    void update(Driver driver);

    void delete(String id);

    Driver findById(String id);

    List<Driver> findAll();

}
