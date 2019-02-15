package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository <Role, BigInteger> {
//	@Query(nativeQuery=true)
//	Set<RolesOnHP> getRolesOnHP();
	
	@Query(nativeQuery=true)
    ArrayList<RoleResult> getRoles(@Param("user_id") long user_id);
	
	@Query(nativeQuery=true)
    Set<RoleResult> getAllRoles();
}
