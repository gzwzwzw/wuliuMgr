package com.logistics.service.warehouse;

import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.Warehouse;
import com.logistics.repository.warehouse.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("仓库不存在: " + warehouseId));
    }

    public List<Warehouse> searchWarehouses(String location) {
        return warehouseRepository.findByLocationContaining(location);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Warehouse warehouseDetails, Long warehouseId) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        warehouse.setLocation(warehouseDetails.getLocation());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long warehouseId) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        warehouseRepository.delete(warehouse);
    }
}