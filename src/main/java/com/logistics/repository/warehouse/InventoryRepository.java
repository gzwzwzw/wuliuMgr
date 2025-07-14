package com.logistics.repository.warehouse;

import com.logistics.model.Inventory;
import com.logistics.model.Product;
import com.logistics.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // 根据仓库ID和产品ID查找库存
    Optional<Inventory> findByWarehouseIdAndProductId(Long warehouseId, Long productId);

    // 根据仓库ID查找库存
    List<Inventory> findByWarehouseId(Long warehouseId);

    // 根据产品ID查找库存
    List<Inventory> findByProductId(Long productId);

    // 根据产品名称模糊查询库存
    @Query("SELECT i FROM Inventory i WHERE i.product.name LIKE %:productName%")
    List<Inventory> findByProductNameContaining(@Param("productName") String productName);

    // 根据多个产品查询库存
    List<Inventory> findByProductIn(List<Product> products);

    // 查找有足够库存的仓库
    @Query("SELECT i.warehouse FROM Inventory i " +
            "WHERE i.product.productId = :productId " +
            "AND i.quantity >= :quantity")
    List<Warehouse> findWarehousesWithSufficientStock(
            @Param("productId") Long productId,
            @Param("quantity") int quantity
    );
}