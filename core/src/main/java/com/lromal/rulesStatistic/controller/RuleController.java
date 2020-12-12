package com.lromal.rulesStatistic.controller;

import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.repository.RuleRepository;
import com.lromal.rulesStatistic.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/rules")
public class RuleController {

	@Autowired
	private RuleService ruleService;

	@PostMapping(path="/add")
	public @ResponseBody Rule add (@RequestParam String name, @RequestParam(required = false) String notes) {

		return ruleService.add(name, notes);
	}

	@PostMapping(path="/update")
	public @ResponseBody void update (@RequestParam Long id, @RequestParam String name, @RequestParam(required = false) String notes) {

		ruleService.update(id, name, notes);
	}

	@GetMapping(path="/getAll")
	public @ResponseBody Iterable<Rule> getAll() {

		return ruleService.getAll();
	}

	@DeleteMapping(path="/delete")
	public @ResponseBody void delete (@RequestParam Long id) {

		ruleService.delete(id);

	}
}
