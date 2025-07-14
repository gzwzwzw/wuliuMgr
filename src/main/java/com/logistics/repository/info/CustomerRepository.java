package com.logistics.repository.info;

import com.logistics.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 根据名称模糊查询客户
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    List<Customer> findByNameContaining(@Param("name") String name);

    // 根据联系人查询客户
    List<Customer> findByContactPerson(String contactPerson);

    // 根据地址模糊查询客户
    @Query("SELECT c FROM Customer c WHERE c.address LIKE %:address%")
    List<Customer> findByAddressContaining(@Param("address") String address);
}