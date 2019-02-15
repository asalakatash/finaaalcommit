package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PermissionRepository extends CrudRepository <Permission, Long> {
	@Query(nativeQuery=true)
    Set<pojoPermission> getPermissions();
    
	@Query(nativeQuery=true)
    ArrayList<PermissionResult> getPermissionsForRole(@Param("role_id") BigInteger role_id);
}
