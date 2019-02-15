package com.Admin.AdminApp.domain;
import java.math.BigInteger;



public class RoleResult {
	
	private BigInteger RoleId;
	private String Role_name;
	private String Description;
	
	public RoleResult()
	{
		
	}
	
	
	public RoleResult(BigInteger roleId, String role_name, String description) {
		super();
		RoleId = roleId;
		Role_name = role_name;
		Description = description;
	}

	public BigInteger getRoleId() {
		return RoleId;
	}
	public void setRoleId(BigInteger roleId) {
		RoleId = roleId;
	}
	public String getRole_name() {
		return Role_name;
	}
	public void setRole_name(String role_name) {
		Role_name = role_name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	

}
