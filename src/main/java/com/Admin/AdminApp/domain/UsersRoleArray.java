package com.Admin.AdminApp.domain;

import java.util.Set;

public class UsersRoleArray {
	String username;
	Set<Role> roles;
	public UsersRoleArray() {}
	public UsersRoleArray(String username, Set<Role> roles) {
		super();
		this.username = username;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
