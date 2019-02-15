package com.Admin.AdminApp.domain;

public class pojoPermission {
	String Per_id;
	String Per_name;
	public pojoPermission() {
	}
	public pojoPermission(String per_id, String per_name) {
		super();
		Per_id = per_id;
		Per_name = per_name;
	}
	public String getPerid() {
		return Per_id;
	}
	public void setPerid(String per_id) {
		Per_id = per_id;
	}
	public String getPer_name() {
		return Per_name;
	}
	public void setPer_name(String per_name) {
		this.Per_name = per_name;
	}
	
}
