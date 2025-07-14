package com.logistics.service.transport;

import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.FreightRule;
import com.logistics.repository.transport.FreightRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreightRuleService {

    public FreightRuleService(FreightRuleRepository freightRuleRepository) {
        this.freightRuleRepository = freightRuleRepository;
    }

    private final FreightRuleRepository freightRuleRepository;

    public List<FreightRule> getAllFreightRules() {
        return freightRuleRepository.findAllByOrderByMinDistanceAsc();
    }

    public FreightRule getFreightRuleById(Long ruleId) {
        return freightRuleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("运费规则不存在: " + ruleId));
    }

    public FreightRule createFreightRule(FreightRule freightRule) {
        validateFreightRule(freightRule);
        return freightRuleRepository.save(freightRule);
    }

    public FreightRule updateFreightRule(Long ruleId, FreightRule freightRuleDetails) {
        FreightRule freightRule = getFreightRuleById(ruleId);
        freightRule.setMinDistance(freightRuleDetails.getMinDistance());
        freightRule.setMaxDistance(freightRuleDetails.getMaxDistance());
        freightRule.setUnitPrice(freightRuleDetails.getUnitPrice());

        validateFreightRule(freightRule);
        return freightRuleRepository.save(freightRule);
    }

    public void deleteFreightRule(Long ruleId) {
        FreightRule freightRule = getFreightRuleById(ruleId);
        freightRuleRepository.delete(freightRule);
    }

    private void validateFreightRule(FreightRule freightRule) {
        // 检查距离范围是否有效
        if (freightRule.getMinDistance() >= freightRule.getMaxDistance()) {
            throw new IllegalArgumentException("最小距离必须小于最大距离");
        }

        // 检查是否与其他规则重叠
        List<FreightRule> rules = freightRuleRepository.findAll();
        for (FreightRule rule : rules) {
            if (rule.getRuleId().equals(freightRule.getRuleId())) continue;

            if (freightRule.getMinDistance() < rule.getMaxDistance() &&
                    freightRule.getMaxDistance() > rule.getMinDistance()) {
                throw new IllegalArgumentException("运费规则与现有规则重叠: " + rule.getRuleId());
            }
        }
    }
}