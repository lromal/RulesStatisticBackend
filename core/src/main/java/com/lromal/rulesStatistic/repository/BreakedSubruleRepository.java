package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.BreakedSubrule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BreakedSubruleRepository extends CrudRepository<BreakedSubrule, Long> {

    List<BreakedSubrule> findByBreakedRuleId(Long breakedRuleId);
    List<BreakedSubrule> findByBreakedRuleIdAndRuleId(Long breakedRuleId, Long ruleId);

}
