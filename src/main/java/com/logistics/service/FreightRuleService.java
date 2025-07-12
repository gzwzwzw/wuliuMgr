package com.logistics.service;

import com.logistics.model.FreightRule;
import com.logistics.repository.FreightRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreightRuleService {
    private final FreightRuleRepository freightRuleRepository;

    public List<FreightRule> getAllRules() {
        return freightRuleRepository.findAllByOrderByMinDistanceAsc();
    }

    public FreightRule createRule(FreightRule rule) {
        return freightRuleRepository.save(rule);
    }

    public void deleteRule(Long id) {
        freightRuleRepository.deleteById(id);
    }

    public double calculateFreight(double distance) {
        List<FreightRule> rules = getAllRules();
        // 实现运费计算逻辑
        return 0.0;
    }
}