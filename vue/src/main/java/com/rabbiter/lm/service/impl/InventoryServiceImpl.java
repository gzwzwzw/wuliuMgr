package com.rabbiter.lm.service.impl;

import com.rabbiter.lm.model.entity.Inventory;
import com.rabbiter.lm.repository.InventoryRepository;
import com.rabbiter.lm.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByCommodityId(String cid) {
        return inventoryRepository.findAllByCid(cid);
    }

    @Override
    public List<Inventory> findByWarehouseId(String wid) {
        return inventoryRepository.findAllByWid(wid);
    }

}
