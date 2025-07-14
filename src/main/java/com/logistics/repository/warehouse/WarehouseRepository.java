package com.logistics.repository.warehouse;

import com.logistics.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    // 根据位置模糊查询仓库
    @Query("SELECT w FROM Warehouse w WHERE w.location LIKE %:location%")
    List<Warehouse> findByLocationContaining(@Param("location") String location);

    // 查找有足够库存的仓库
    @Query("SELECT w FROM Warehouse w " +
            "JOIN w.inventories i " +
            "WHERE i.product.productId = :productId " +
            "AND i.quantity >= :quantity")
    List<Warehouse> findWarehousesWithSufficientStock(
            @Param("productId") Long productId,
            @Param("quantity") int quantity
    );

    // 查找第一个有足够库存的仓库
    @Query("SELECT w FROM Warehouse w " +
            "JOIN w.inventories i " +
            "WHERE i.product.productId = :productId " +
            "AND i.quantity >= :quantity " +
            "ORDER BY w.warehouseId ASC LIMIT 1")
    Optional<Warehouse> findFirstWithSufficientStock(
            @Param("productId") Long productId,
            @Param("quantity") int quantity
    );
}