package com.logistics.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String location;

    @OneToMany(mappedBy = "warehouse")
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "warehouse")
    private List<Vehicle> vehicles;
}