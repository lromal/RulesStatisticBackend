package com.lromal.rulesStatistic.model.dto;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.BreakedSubrule;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class BreakedRuleDTO {

	private BreakedRule breakedRule;
	private List<BreakedSubrule> breakedSubrules;

	public BreakedRuleDTO(BreakedRule breakedRule, List<BreakedSubrule> breakedSubrules) {
		this.breakedRule = breakedRule;
		this.breakedSubrules = breakedSubrules;
	}
}
