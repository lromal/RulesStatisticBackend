package com.lromal.rulesStatistic.service;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.BreakedSubrule;
import com.lromal.rulesStatistic.model.Subrule;
import com.lromal.rulesStatistic.model.dto.BreakedRuleDTO;
import com.lromal.rulesStatistic.model.dto.BreakedRuleStatisticsDTO;
import com.lromal.rulesStatistic.model.dto.BreakedRuleSumDTO;
import com.lromal.rulesStatistic.repository.BreakedRuleRepository;
import com.lromal.rulesStatistic.repository.BreakedSubruleRepository;
import com.lromal.rulesStatistic.repository.RuleRepository;
import com.lromal.rulesStatistic.repository.SubruleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BreakedRuleService {

	@Autowired
	private RuleRepository ruleRepository;
	@Autowired
	private BreakedRuleRepository breakedRuleRepository;
	@Autowired
	private BreakedSubruleRepository breakedSubruleRepository;
	@Autowired
	private SubruleRepository subruleRepository;

	public BreakedRule add(Date date, final Long ruleId, List<Long> subruleIds) {

		BreakedRule breakedRule = breakedRuleRepository.findByBreakDate(date);

		if(breakedRule == null) {
			breakedRule = new BreakedRule();
			breakedRule.setBreakDate(date);
			breakedRuleRepository.save(breakedRule);
		} else {
			List<BreakedSubrule> breakedSubrule = breakedSubruleRepository.findByBreakedRuleIdAndRuleId(breakedRule.getId(), ruleId);
			if(breakedSubrule.size() > 0) return breakedRule;
		}

		final Long breakedRuleId = breakedRule.getId();

		subruleIds.stream().forEach(subruleId -> {

			BreakedSubrule breakedSubrule = new BreakedSubrule();

			breakedSubrule.setBreakedRuleId(breakedRuleId);
			breakedSubrule.setRuleId(ruleId);
			breakedSubrule.setSubruleId(subruleId);

			breakedSubruleRepository.save(breakedSubrule);
		});




		return breakedRule;
	}


	public void update(Long breakedRuleId, List<Long> newSubruleIds, Long ruleId) {

		List<BreakedSubrule> breakedSubrules = breakedSubruleRepository.findByBreakedRuleIdAndRuleId(breakedRuleId, ruleId);

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


	public List<BreakedRuleSumDTO> getGroupedSumByDatePeriod(Date from, Date to) {
		List<BreakedRuleSumDTO> breakedRuleSumDTOs = getSumByDatePeriod(from, to);
		Map<Long, BreakedRuleSumDTO> groupedBreakedRuleSumDTOs = new HashMap<>();

		breakedRuleSumDTOs.forEach(it -> {
			BreakedRuleSumDTO breakedRuleSumDTO = groupedBreakedRuleSumDTOs.get(it.getRuleId());

			if(breakedRuleSumDTO == null) {
				groupedBreakedRuleSumDTOs.put(it.getRuleId(), it);
				return;
			}

			breakedRuleSumDTO.setViolatesNumber(breakedRuleSumDTO.getViolatesNumber() + it.getViolatesNumber());

			if(!it.getViolateStatus()) {
				breakedRuleSumDTO.setViolateStatus(it.getViolateStatus());
			}

		});

		return new ArrayList<>(groupedBreakedRuleSumDTOs.values());
	}
	public List<BreakedRuleSumDTO> getSumByDatePeriod(Date from, Date to) {
		List<BreakedRule> breakedRules = breakedRuleRepository.findAllBetweenDates(from, to);
		Map<Long, Integer> breakedSubrulesStatistics = new HashMap<>();
		List<BreakedRuleSumDTO> result = new ArrayList<>();
		Set<Long> subruleIds = new HashSet<>();

		breakedRules.forEach(breakedRule -> {
			List<BreakedSubrule> breakedSubrules = breakedSubruleRepository.findByBreakedRuleId(breakedRule.getId());

			breakedSubrules.forEach(breakedSubrule -> {
				Integer count = breakedSubrulesStatistics.get(breakedSubrule.getSubruleId()) == null ? 0 : breakedSubrulesStatistics.get(breakedSubrule.getSubruleId());

				breakedSubrulesStatistics.put(breakedSubrule.getSubruleId(), count + 1);

				subruleIds.add(breakedSubrule.getSubruleId());
			});
		});

		List<Subrule> subrules = subruleRepository.findByIdIn(new ArrayList<>(subruleIds));

		breakedSubrulesStatistics.forEach((key, value) -> {
			Subrule subrule = subrules.stream().filter(it -> it.getId() == key).findFirst().orElse(null);

			if(subrule == null) return;

			BreakedRuleSumDTO breakedRuleSumDTO = new BreakedRuleSumDTO();
			breakedRuleSumDTO.setRuleId(subrule.getRuleId());
			breakedRuleSumDTO.setSubruleId(key);
			breakedRuleSumDTO.setViolatesNumber(value);
			breakedRuleSumDTO.setViolateStatus(subrule.getAllowedViolatesNumber() >= value);

			result.add(breakedRuleSumDTO);
		});

		return result;
	}
	public List<BreakedRuleStatisticsDTO> getByDatePeriod(Date from, Date to) {

		List<BreakedRule> breakedRules = breakedRuleRepository.findAllBetweenDates(from, to);
		List<BreakedRuleStatisticsDTO> result = new ArrayList<>();

		breakedRules.forEach(breakedRule -> {

			BreakedRuleStatisticsDTO breakedRuleStatisticsDTO = new BreakedRuleStatisticsDTO();
			List<BreakedSubrule> breakedSubrules = breakedSubruleRepository.findByBreakedRuleId(breakedRule.getId());

			breakedRuleStatisticsDTO.setCurrentDate(breakedRule.getBreakDate());
			breakedRuleStatisticsDTO.setApproveDate(breakedRule.getApproveDate());
			Map<Long, Integer> subrulesStatistics = new HashMap<>();

			breakedSubrules.forEach(breakedSubrule -> {
				Integer count = subrulesStatistics.get(breakedSubrule.getRuleId()) == null ? 0 : subrulesStatistics.get(breakedSubrule.getRuleId());
				subrulesStatistics.put(breakedSubrule.getRuleId(), count + 1);
			});

			breakedRuleStatisticsDTO.setBreakedRules(subrulesStatistics);

			result.add(breakedRuleStatisticsDTO);
		});
		return result;
	}

	public Iterable<BreakedRule> getAll() {

		return breakedRuleRepository.findAll();
	}

	public BreakedRuleDTO get(Long id) {

		BreakedRule breakedRule = breakedRuleRepository.findById(id).orElse(null);

		if(breakedRule == null) return null;

		List<BreakedSubrule> breakedSubrules = breakedSubruleRepository.findByBreakedRuleId(id);

		return new BreakedRuleDTO(breakedRule, breakedSubrules);
	}


	public void delete(Long id) {

		BreakedRule breakedRule = breakedRuleRepository.findById(id).orElse(null);

		if(breakedRule == null) return;

		breakedRuleRepository.delete(breakedRule);

	}

	public Date getFirstBreakedRuleDate() {
		Optional<BreakedRule> breakedRule = breakedRuleRepository.findFirstByOrderByBreakDateAsc();

		if(!breakedRule.isPresent()) return new Date();

		return breakedRule.get().getBreakDate();
	}
}
