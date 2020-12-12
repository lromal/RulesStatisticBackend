package com.lromal.rulesStatistic.service;

import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RuleService {

	@Autowired
	private RuleRepository ruleRepository;

	public Rule add(String name, String notes) {

		Rule rule = new Rule();
		rule.setName(name);
		rule.setNotes(notes);
		ruleRepository.save(rule);
		return rule;
	}


	public void update(Long id, String name, String notes) {

		Rule rule = ruleRepository.findById(id).orElse(null);

		if(rule == null) return;

		rule.setName(name);
		rule.setNotes(notes);
		ruleRepository.save(rule);
	}


	public Iterable<Rule> getAll() {

		return ruleRepository.findAll();
	}


	public void delete(Long id) {

		Rule rule = ruleRepository.findById(id).orElse(null);

		if(rule == null) return;

		ruleRepository.delete(rule);

	}
}
