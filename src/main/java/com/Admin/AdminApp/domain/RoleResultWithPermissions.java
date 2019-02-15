package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.ArrayList;

public class RoleResultWithPermissions {
	private BigInteger RoleId;
	private String Role_name;
	private String Description;
	private ArrayList<PermissionResult> permissions;
	
	public RoleResultWithPermissions() {}
	

	public RoleResultWithPermissions(String role_name, String description, ArrayList<PermissionResult> permissions) {
		super();
		Role_name = role_name;
		Description = description;
		this.permissions = permissions;
	}


	public RoleResultWithPermissions(BigInteger roleId, String role_name, String description) {
		super();
		RoleId = roleId;
		Role_name = role_name;
		Description = description;
	}


	public RoleResultWithPermissions(BigInteger roleId, String role_name, String description,
			ArrayList<PermissionResult> permissions) {
		super();
		RoleId = roleId;
		Role_name = role_name;
		Description = description;
		this.permissions = permissions;
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


	public ArrayList<PermissionResult> getPermissions() {
		return permissions;
	}


	public void setPermissions(ArrayList<PermissionResult> permissions) {
		this.permissions = permissions;
	}
	
	
}
