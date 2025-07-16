package com.rabbiter.lm.service.impl;

import com.rabbiter.lm.model.entity.Sale;
import com.rabbiter.lm.repository.SaleRepository;
import com.rabbiter.lm.service.SaleService;
import com.rabbiter.lm.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleRepository saleRepository;

    @Override
    public Sale save(Sale sale) {
        sale.setCreateAt(DataTimeUtil.getNowTimeString());
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> searchByCompany(String name) {
        return saleRepository.findAllByCompanyLike(name);
    }

}
