package com.rabbiter.lm.controller;

import com.rabbiter.lm.annotation.Log;
import com.rabbiter.lm.model.entity.Warehouse;
import com.rabbiter.lm.model.enums.BusincessType;
import com.rabbiter.lm.service.WarehouseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_WAREHOUSE')")
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    @Log(moudle = "仓库管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Warehouse save(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse);
    }

    @Log(moudle = "仓库管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Warehouse> findAll() {
        return warehouseService.findAll();
    }

    @Log(moudle = "仓库管理",type = BusincessType.DELETE)
    @DeleteMapping("")
    public void delete(String id) {
        warehouseService.delete(id);
    }

}
