package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(String id);

    Vehicle findById(String id);

    List<Vehicle> findAll();

}
