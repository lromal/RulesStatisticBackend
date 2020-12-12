package com.lromal.rulesStatistic.service;

import com.lromal.rulesStatistic.model.Rule;
import com.lromal.rulesStatistic.model.Subrule;
import com.lromal.rulesStatistic.repository.RuleRepository;
import com.lromal.rulesStatistic.repository.SubruleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SubruleService {

	@Autowired
	private SubruleRepository subruleRepository;

	public Subrule add(Long ruleId, String name, Integer allowedViolatesNumber, String description) {

		Subrule subrule = new Subrule();
		subrule.setName(name);
		subrule.setRuleId(ruleId);

		if(allowedViolatesNumber == null) {
			allowedViolatesNumber = 1;
		}

		subrule.setAllowedViolatesNumber(allowedViolatesNumber);
		subrule.setDescription(description);
		subruleRepository.save(subrule);
		return subrule;
	}

	public List<Subrule> getAll(Long ruleId) {

		return subruleRepository.findByRuleId(ruleId);

	}

	public void delete(Long id) {

		Subrule subrule = subruleRepository.findById(id).orElse(null);

		if(subrule == null) return;

		subruleRepository.delete(subrule);

	}

	public void update(Long id, String name, Integer allowedViolatesNumber, String description) {

		Subrule subrule = subruleRepository.findById(id).orElse(null);

		if(subrule == null) return;

		subrule.setName(name);

		if(allowedViolatesNumber != null) {
			subrule.setAllowedViolatesNumber(allowedViolatesNumber);
		}

		subrule.setDescription(description);

		subruleRepository.save(subrule);
	}


}
