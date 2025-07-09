package com.logistics.repository;

import com.logistics.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("SELECT w FROM Warehouse w WHERE w.location LIKE %:location%")
    List<Warehouse> findByLocationContaining(@Param("location") String location);

    @Query("SELECT w FROM Warehouse w " +
            "JOIN w.inventories i " +
            "WHERE i.product.productId = :productId AND i.quantity >= :quantity")
    List<Warehouse> findWarehousesWithSufficientStock(@Param("productId") Long productId,
                                                      @Param("quantity") int quantity);

    @Query(value = "SELECT w.* FROM warehouse w " +
            "JOIN inventory i ON w.warehouse_id = i.warehouse_id " +
            "WHERE i.product_id = :productId AND i.quantity >= :quantity " +
            "LIMIT 1", nativeQuery = true)
    Optional<Warehouse> findFirstWithStock(@Param("productId") Long productId,
                                           @Param("quantity") int quantity);
}