package com.lromal.rulesStatistic.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "breaked_rule")
@Data
public class BreakedRule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="break_date")
	private Date breakDate;

	@Column(name="approve_date")
	private Date approveDate;

}
