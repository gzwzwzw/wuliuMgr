package com.logistics.repository;

import com.logistics.model.FreightRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreightRuleRepository extends JpaRepository<FreightRule, Long> {

    @Query("SELECT fr FROM FreightRule fr ORDER BY fr.minDistance ASC")
    List<FreightRule> findAllByOrderByMinDistanceAsc();

    @Query("SELECT fr FROM FreightRule fr " +
            "WHERE :distance >= fr.minDistance AND :distance <= fr.maxDistance")
    Optional<FreightRule> findApplicableRule(@Param("distance") double distance);
}