package com.rabbiter.lm.service;

import com.rabbiter.lm.model.entity.Distribution;

import java.util.List;

public interface DistributionService {

    Distribution save(Distribution distribution) throws Exception;

    List<Distribution> findAll();

}
