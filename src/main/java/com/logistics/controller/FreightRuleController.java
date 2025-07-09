package com.logistics.controller;

import com.logistics.dto.FreightRuleDTO;
import com.logistics.service.FreightRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freight-rules")
@Tag(name = "运费规则管理", description = "运费规则的增删改查和计算")
public class FreightRuleController {

    private final FreightRuleService freightRuleService;

    public FreightRuleController(FreightRuleService freightRuleService) {
        this.freightRuleService = freightRuleService;
    }

    @GetMapping
    @Operation(summary = "获取所有运费规则")
    public ResponseEntity<List<FreightRuleDTO>> getAllFreightRules() {
        return ResponseEntity.ok(freightRuleService.getAllFreightRules());
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取运费规则")
    public ResponseEntity<FreightRuleDTO> getFreightRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(freightRuleService.getFreightRuleById(id));
    }

    @PostMapping
    @Operation(summary = "创建新运费规则")
    public ResponseEntity<FreightRuleDTO> createFreightRule(@Valid @RequestBody FreightRuleDTO freightRuleDTO) {
        FreightRuleDTO savedRule = freightRuleService.createFreightRule(freightRuleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRule);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新运费规则")
    public ResponseEntity<FreightRuleDTO> updateFreightRule(
            @PathVariable Long id,
            @Valid @RequestBody FreightRuleDTO freightRuleDTO) {
        return ResponseEntity.ok(freightRuleService.updateFreightRule(id, freightRuleDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除运费规则")
    public ResponseEntity<Void> deleteFreightRule(@PathVariable Long id) {
        freightRuleService.deleteFreightRule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calculate")
    @Operation(summary = "计算运费")
    public ResponseEntity<Double> calculateFreight(@RequestParam double distance) {
        return ResponseEntity.ok(freightRuleService.calculateFreight(distance));
    }

    @GetMapping("/applicable")
    @Operation(summary = "获取适用的运费规则")
    public ResponseEntity<FreightRuleDTO> getApplicableFreightRule(@RequestParam double distance) {
        return ResponseEntity.ok(freightRuleService.getApplicableFreightRule(distance));
    }
}