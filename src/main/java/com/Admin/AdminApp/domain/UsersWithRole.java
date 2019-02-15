package com.Admin.AdminApp.domain;

public class UsersWithRole {
	String UserName ;
	String Phone;
	String Roles;
	String Login;
	
	public UsersWithRole() {}
	public UsersWithRole(String userName, String phone,String roles,String login) {
		super();
		UserName = userName;
		Phone = phone;
		Roles = roles;
		Login = login;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getRoles() {
		return Roles;
	}
	public void setRoles(String roles) {
		Roles = roles;
	}
	

	
	
}
