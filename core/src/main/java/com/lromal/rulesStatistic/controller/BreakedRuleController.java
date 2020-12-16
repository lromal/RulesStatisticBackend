package com.lromal.rulesStatistic.controller;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.model.dto.BreakedRuleDTO;
import com.lromal.rulesStatistic.model.dto.BreakedRuleStatisticsDTO;
import com.lromal.rulesStatistic.model.dto.BreakedRuleSumDTO;
import com.lromal.rulesStatistic.service.BreakedRuleService;
import com.lromal.rulesStatistic.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/breakedRules")
public class BreakedRuleController {

	@Autowired
	private RuleService ruleService;
	@Autowired
	private BreakedRuleService breakedRuleService;

	@PostMapping(path="/add")
	public @ResponseBody
	BreakedRule add (@RequestParam @DateTimeFormat(pattern="dd.MM.yyyy") Date date, @RequestParam Long ruleId, @RequestParam List<Long> subruleIds) {

		return breakedRuleService.add(date, ruleId, subruleIds);
	}

	@PostMapping(path="/update")
	public @ResponseBody void update (@RequestParam Long breakedRuleId, @RequestParam Long ruleId, @RequestParam List<Long> subruleIds) {

		breakedRuleService.update(breakedRuleId, subruleIds, ruleId);
	}

	@GetMapping(path="/getAll")
	public @ResponseBody Iterable<BreakedRule> getAll() {

		return breakedRuleService.getAll();
	}

	@GetMapping(path="/get")
	public @ResponseBody
	BreakedRuleDTO get(Long id) {

		return breakedRuleService.get(id);
	}

	@GetMapping(path="/getByDatePeriod")
	public @ResponseBody
	List<BreakedRuleStatisticsDTO> getByDatePeriod(@RequestParam @DateTimeFormat(pattern="dd.MM.yyyy") Date from, @RequestParam @DateTimeFormat(pattern="dd.MM.yyyy") Date to) {

		return breakedRuleService.getByDatePeriod(from, to);
	}

	@GetMapping(path="/getByDatePeriod2")
	public @ResponseBody
	List<BreakedRuleSumDTO> getByDatePeriod2(@RequestParam @DateTimeFormat(pattern="dd.MM.yyyy") Date from, @RequestParam @DateTimeFormat(pattern="dd.MM.yyyy") Date to) {

		return breakedRuleService.getByDatePeriod2(from, to);
	}

	@DeleteMapping(path="/delete")
	public @ResponseBody void delete (@RequestParam Long id) {

		breakedRuleService.delete(id);

	}
}
