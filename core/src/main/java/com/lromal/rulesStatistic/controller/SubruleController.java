package com.lromal.rulesStatistic.controller;

import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.model.Subrule;
import com.lromal.rulesStatistic.service.SubruleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/subrule")
public class SubruleController {

	@Autowired
	private SubruleService subruleService;

	@PostMapping(path="/add")
	public @ResponseBody Subrule add (@RequestParam Long ruleId, @RequestParam String name,
				 @RequestParam(required = false) Integer allowedViolatesNumber, @RequestParam(required = false) String description) {

		return subruleService.add(ruleId, name, allowedViolatesNumber, description);
	}

	@PostMapping(path="/update")
	public @ResponseBody void update (@RequestParam Long id, @RequestParam String name,
									  @RequestParam(required = false) Integer allowedViolatesNumber, @RequestParam(required = false) String description) {
		subruleService.update(id, name, allowedViolatesNumber, description);
	}

	@GetMapping(path="/getAll")
	public @ResponseBody List<Subrule> getAll(@RequestParam Long ruleId) {

		return subruleService.getAll(ruleId);

	}

	@DeleteMapping(path="/delete")
	public @ResponseBody void delete (@RequestParam Long id) {

		subruleService.delete(id);

	}
}
