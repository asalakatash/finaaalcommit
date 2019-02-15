package com.Admin.AdminApp.domain;

import java.math.BigInteger;

public class PermissionResult {
	private long perId;
	private String per_name;
	public PermissionResult() {}
	public PermissionResult(long perId, String per_name) {
		this.perId = perId;
		this.per_name = per_name;
	}
	public long getPerId() {
		return perId;
	}
	public void setPerId(long perId) {
		this.perId = perId;
	}
	public String getPer_name() {
		return per_name;
	}
	public void setPer_name(String per_name) {
		this.per_name = per_name;
	}
	
}
