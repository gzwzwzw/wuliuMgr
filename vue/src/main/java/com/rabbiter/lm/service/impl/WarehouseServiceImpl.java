package com.rabbiter.lm.service.impl;

import com.rabbiter.lm.model.entity.Warehouse;
import com.rabbiter.lm.repository.WareHouseRepository;
import com.rabbiter.lm.service.WarehouseService;
import com.rabbiter.lm.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WareHouseRepository wareHouseRepository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        warehouse.setCreateAt(DataTimeUtil.getNowTimeString());
        return wareHouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        return wareHouseRepository.findAll();
    }

    @Override
    public void delete(String id) {
        wareHouseRepository.deleteById(id);
    }

}
