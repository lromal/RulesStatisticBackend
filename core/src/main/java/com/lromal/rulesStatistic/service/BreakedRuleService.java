package com.lromal.rulesStatistic.service;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.BreakedSubrule;
import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.model.dto.BreakedRuleDTO;
import com.lromal.rulesStatistic.repository.BreakedRuleRepository;
import com.lromal.rulesStatistic.repository.BreakedSubruleRepository;
import com.lromal.rulesStatistic.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BreakedRuleService {

	@Autowired
	private RuleRepository ruleRepository;
	@Autowired
	private BreakedRuleRepository breakedRuleRepository;
	@Autowired
	private BreakedSubruleRepository breakedSubruleRepository;

	public BreakedRule add(Date date, final Long ruleId, List<Long> subruleIds) {

		BreakedRule breakedRule = new BreakedRule();
		breakedRule.setBreakDate(date);
		breakedRuleRepository.save(breakedRule);

		subruleIds.stream().forEach(subruleId -> {

			BreakedSubrule breakedSubrule = new BreakedSubrule();

			breakedSubrule.setBreakedRuleId(breakedRule.getId());
			breakedSubrule.setRuleId(ruleId);
			breakedSubrule.setSubruleId(subruleId);

			breakedSubruleRepository.save(breakedSubrule);
		});




		return breakedRule;
	}


	public void update(Long breakedRuleId, List<Long> newSubruleIds, Long ruleId) {

		List<BreakedSubrule> breakedSubrules = breakedSubruleRepository.getByBreakedRuleIdAndRuleId(breakedRuleId, ruleId);

		if(breakedSubrules.size() == 0) return;

		List<Long> subruleIds = breakedSubrules.stream().map(BreakedSubrule::getSubruleId).collect(Collectors.toList());

		breakedSubrules.forEach(it -> {

			if(newSubruleIds.contains(it.getSubruleId())) return;

			breakedSubruleRepository.delete(it);
		});

		newSubruleIds.forEach(it -> {

			if(subruleIds.contains(it)) return;

			BreakedSubrule breakedSubrule = new BreakedSubrule();
			breakedSubrule.setRuleId(ruleId);
			breakedSubrule.setSubruleId(it);
			breakedSubrule.setBreakedRuleId(breakedRuleId);
			breakedSubruleRepository.save(breakedSubrule);
		});

	}


	public Iterable<Rule> getByDatePeriod(Date from, Date to) {
		return null;
	}

	public Iterable<BreakedRule> getAll() {

		return breakedRuleRepository.findAll();
	}

	public BreakedRuleDTO get(Long id) {

		BreakedRule breakedRule = breakedRuleRepository.findById(id).orElse(null);

		if(breakedRule == null) return null;

		List<BreakedSubrule> breakedSubrules = breakedSubruleRepository.getByBreakedRuleId(id);

		return new BreakedRuleDTO(breakedRule, breakedSubrules);
	}


	public void delete(Long id) {

		BreakedRule breakedRule = breakedRuleRepository.findById(id).orElse(null);

		if(breakedRule == null) return;

		breakedRuleRepository.delete(breakedRule);

	}
}
