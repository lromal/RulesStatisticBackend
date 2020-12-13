package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.Rule;
import org.springframework.data.repository.CrudRepository;


public interface BreakedRuleRepository extends CrudRepository<BreakedRule, Long> {

}
