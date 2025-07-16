package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Sale;

import java.util.List;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

    List<Sale> searchByCompany(String name);

}
