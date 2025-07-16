package com.rabbiter.lm.repository;

import com.rabbiter.lm.model.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {

    Commodity findByName(String name);

    List<Commodity> findByNameLike(String name);

}
