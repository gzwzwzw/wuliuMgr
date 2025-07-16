package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory save(Inventory inventory);

    List<Inventory> findAll();

    List<Inventory> findByCommodityId(String cid);

    List<Inventory> findByWarehouseId(String wid);

}
