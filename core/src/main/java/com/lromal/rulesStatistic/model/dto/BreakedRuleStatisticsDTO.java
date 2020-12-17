package com.lromal.rulesStatistic.model.dto;

import com.lromal.rulesStatistic.model.BreakedRule;
import com.lromal.rulesStatistic.model.BreakedSubrule;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class BreakedRuleStatisticsDTO {

	private Date currentDate;
	private Date approveDate;
	private Map<Long,Integer> breakedRules;

}
