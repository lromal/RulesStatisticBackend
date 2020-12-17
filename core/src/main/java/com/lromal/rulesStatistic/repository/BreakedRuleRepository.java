package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.BreakedRule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface BreakedRuleRepository extends CrudRepository<BreakedRule, Long> {
    BreakedRule findByBreakDate(Date date);

    @Query(value = "from breaked_rule where break_date BETWEEN :startDate AND :endDate")
    List<BreakedRule> findAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

    Optional<BreakedRule> findFirstByOrderByBreakDateAsc();
}
