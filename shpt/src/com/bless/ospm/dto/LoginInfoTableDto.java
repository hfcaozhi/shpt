package com.bless.ospm.dto;

public class LoginInfoTableDto {
	private int loginId;
	private String loginCode;
	private int statuId;
	private String statuValue;
	
	public LoginInfoTableDto(){}
	
	public LoginInfoTableDto(int loginId, String loginCode, int statuId,
			String statuValue) {
		super();
		this.loginId = loginId;
		this.loginCode = loginCode;
		this.statuId = statuId;
		this.statuValue = statuValue;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public int getStatuId() {
		return statuId;
	}
	public void setStatuId(int statuId) {
		this.statuId = statuId;
	}
	public String getStatuValue() {
		return statuValue;
	}
	public void setStatuValue(String statuValue) {
		this.statuValue = statuValue;
	}
}
