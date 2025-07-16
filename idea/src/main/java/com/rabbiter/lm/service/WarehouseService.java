package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> findAll();

    void delete(String id);

}
