package com.logistics.repository.transport;

import com.logistics.model.FreightRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreightRuleRepository extends JpaRepository<FreightRule, Long> {

    // 按最小距离升序排序所有规则
    List<FreightRule> findAllByOrderByMinDistanceAsc();

    // 查找适用于给定距离的规则
    @Query("SELECT fr FROM FreightRule fr " +
            "WHERE :distance >= fr.minDistance " +
            "AND :distance <= fr.maxDistance")
    Optional<FreightRule> findApplicableRule(@Param("distance") double distance);
}