package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.model.Subrule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SubruleRepository extends CrudRepository<Subrule, Long> {
    List<Subrule> findByRuleId(Long ruleId);
    List<Subrule> findByIdIn(List<Long> ids);
}
