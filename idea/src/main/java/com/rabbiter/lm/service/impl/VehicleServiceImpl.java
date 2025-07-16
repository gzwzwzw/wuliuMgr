package com.rabbiter.lm.service.impl;

import com.rabbiter.lm.model.entity.Vehicle;
import com.rabbiter.lm.repository.VehicleRepository;
import com.rabbiter.lm.service.VehicleService;
import com.rabbiter.lm.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle.setCreateAt(DataTimeUtil.getNowTimeString());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

}
