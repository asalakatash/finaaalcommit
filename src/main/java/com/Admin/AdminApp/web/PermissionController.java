package com.Admin.AdminApp.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Admin.AdminApp.domain.Permission;
import com.Admin.AdminApp.domain.PermissionRepository;
import com.Admin.AdminApp.domain.pojoPermission;

@RestController
public class PermissionController {
	@Autowired
	private PermissionRepository perRepository;
	
	@RequestMapping("/permissions")
	public Set<pojoPermission> getPermissions() {
		return perRepository.getPermissions();
	}
}
