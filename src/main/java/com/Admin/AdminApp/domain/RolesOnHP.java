package com.Admin.AdminApp.domain;

public class RolesOnHP {
	String RoleName;
	String Description;
	String Per_name;
	String Assigned;
	
	public RolesOnHP() {

	}
	public RolesOnHP(String roleName, String description, String per_name, String assigned) {
		super();
		RoleName = roleName;
		Description = description;
		Per_name = per_name;
		Assigned = assigned;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getPER_NAME() {
		return Per_name;
	}
	public void setPER_NAME(String pER_NAME) {
		Per_name = pER_NAME;
	}
	public String getAssigned() {
		return Assigned;
	}
	public void setAssigned(String assigned) {
		this.Assigned = assigned;
	}
	
	
}
