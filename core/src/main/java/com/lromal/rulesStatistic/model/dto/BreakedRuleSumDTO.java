package com.lromal.rulesStatistic.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class BreakedRuleSumDTO {

	private Long subruleId;
	private Integer violatesNumber;
	private Boolean violateStatus;

}
