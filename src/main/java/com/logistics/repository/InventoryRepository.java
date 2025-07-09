package com.logistics.repository;

import com.logistics.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("SELECT i FROM Inventory i " +
            "WHERE i.warehouse.warehouseId = :warehouseId " +
            "AND i.product.productId = :productId")
    Optional<Inventory> findByWarehouseIdAndProductId(@Param("warehouseId") Long warehouseId,
                                                      @Param("productId") Long productId);

    @Query("SELECT i FROM Inventory i WHERE i.warehouse.warehouseId = :warehouseId")
    List<Inventory> findByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query("SELECT i FROM Inventory i WHERE i.product.productId = :productId")
    List<Inventory> findByProductId(@Param("productId") Long productId);

    @Query("SELECT i FROM Inventory i " +
            "WHERE (:warehouseId IS NULL OR i.warehouse.warehouseId = :warehouseId) " +
            "AND (:productId IS NULL OR i.product.productId = :productId)")
    List<Inventory> findByWarehouseAndProduct(@Param("warehouseId") Long warehouseId,
                                              @Param("productId") Long productId);
}