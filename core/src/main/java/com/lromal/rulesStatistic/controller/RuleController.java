package com.lromal.rulesStatistic.controller;

import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/rules")
public class RuleController {

	@Autowired
	private RuleRepository ruleRepository;

	@PostMapping(path="/addRule")
	public @ResponseBody String addRule (@RequestParam String name) {

		Rule rule = new Rule();
		rule.setName(name);
		ruleRepository.save(rule);
		return "OK";
	}

	@GetMapping(path="/getAllRules")
	public @ResponseBody Iterable<Rule> getAllRules() {

		return ruleRepository.findAll();
	}
}
