package com.lromal.rulesStatistic.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "breaked_subrule")
@Data
public class BreakedSubrule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="breaked_rule_id")
	private Long breakedRuleId;

	@Column(name="subrule_id")
	private Long subruleId;

	@Column(name="rule_id")
	private Long ruleId;

}
