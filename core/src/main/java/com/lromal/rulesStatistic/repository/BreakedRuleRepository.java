package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.BreakedRule;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;


public interface BreakedRuleRepository extends CrudRepository<BreakedRule, Long> {
    BreakedRule findByBreakDate(Date date);
}
