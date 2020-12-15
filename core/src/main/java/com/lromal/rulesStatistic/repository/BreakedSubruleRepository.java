package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.BreakedSubrule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BreakedSubruleRepository extends CrudRepository<BreakedSubrule, Long> {

    List<BreakedSubrule> getByBreakedRuleId(Long breakedRuleId);
    List<BreakedSubrule> getByBreakedRuleIdAndRuleId(Long breakedRuleId, Long ruleId);

}
