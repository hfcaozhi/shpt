package com.bless.ospm.dto;

import java.util.List;

import com.bless.ospm.model.base.HealthIndicator;

/**
 * 健康指标dto
 * @author admin
 *
 */

public class HealthIndicatorRefDto {
	
	private List<HealthIndicator> his;
	private List<HealthIndicator> allHis;
	
	public HealthIndicatorRefDto(List<HealthIndicator> his, List<HealthIndicator> allhis) {
		this.his = his;
		this.allHis = allHis;
	}
	
	public List<HealthIndicator> getHis() {
		return his;
	}
	
	public List<HealthIndicator> getAllHis() {
		return allHis;
	}
	
}
