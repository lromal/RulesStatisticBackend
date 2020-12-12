package com.lromal.rulesStatistic.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subrule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name="rule_id")
	private Long ruleId;

	@Column(name="allowed_violates_number")
	private Integer allowedViolatesNumber;

	private String description;
}
