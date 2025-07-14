package com.logistics.service.warehouse;

import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.Inventory;
import com.logistics.model.Product;
import com.logistics.model.Warehouse;
import com.logistics.repository.warehouse.InventoryRepository;
import com.logistics.repository.info.ProductRepository;
import com.logistics.repository.warehouse.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    public InventoryService(InventoryRepository inventoryRepository, WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Inventory addStock(Long warehouseId, Long productId, int quantity) {
        // 验证仓库
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("仓库不存在: " + warehouseId));

        // 验证商品
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("商品不存在: " + productId));

        // 查找或创建库存记录
        Inventory inventory = inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                .orElseGet(() -> {
                    Inventory newInventory = new Inventory();
                    newInventory.setWarehouse(warehouse);
                    newInventory.setProduct(product);
                    newInventory.setQuantity(0);
                    return inventoryRepository.save(newInventory);
                });

        // 增加库存
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public Inventory reserveStock(Long warehouseId, Long productId, int quantity) {
        // 验证仓库
        warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("仓库不存在: " + warehouseId));

        // 验证商品
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("商品不存在: " + productId));

        // 查找库存记录
        Inventory inventory = inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "仓库 " + warehouseId + " 中没有商品 " + productId + " 的库存记录"));

        // 检查库存是否足够
        if (inventory.getQuantity() < quantity) {
            throw new ResourceNotFoundException(
                    "库存不足: 商品 " + productId + " 在仓库 " + warehouseId +
                            " 的库存为 " + inventory.getQuantity() + ", 需要 " + quantity);
        }

        // 扣减库存
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> searchInventory(Long warehouseId, Long productId, String productName) {
        if (productName != null && !productName.isEmpty()) {
            // 按商品名称搜索
            return inventoryRepository.findByProductNameContaining(productName);
        } else if (warehouseId != null && productId != null) {
            // 按仓库和商品ID精确搜索
            return inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                    .map(List::of)
                    .orElseGet(List::of);
        } else if (warehouseId != null) {
            // 按仓库ID搜索
            return inventoryRepository.findByWarehouseId(warehouseId);
        } else if (productId != null) {
            // 按商品ID搜索
            return inventoryRepository.findByProductId(productId);
        } else {
            // 返回所有库存
            return inventoryRepository.findAll();
        }
    }
}