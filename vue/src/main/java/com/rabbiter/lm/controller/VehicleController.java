package com.rabbiter.lm.controller;

import com.rabbiter.lm.annotation.Log;
import com.rabbiter.lm.model.entity.Vehicle;
import com.rabbiter.lm.model.enums.BusincessType;
import com.rabbiter.lm.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    @Log(moudle = "车辆管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @Log(moudle = "车辆管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @Log(moudle = "车辆管理",type = BusincessType.QUERY)
    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable String id) {
        return vehicleService.findById(id);
    }

    @Log(moudle = "车辆管理",type = BusincessType.DELETE)
    @DeleteMapping("")
    public void delete(String id) {
        vehicleService.delete(id);
    }

}
