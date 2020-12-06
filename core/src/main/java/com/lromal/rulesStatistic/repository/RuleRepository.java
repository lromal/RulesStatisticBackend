package com.lromal.rulesStatistic.repository;

import com.lromal.rulesStatistic.model.Rule;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RuleRepository extends CrudRepository<Rule, Integer> {

}
