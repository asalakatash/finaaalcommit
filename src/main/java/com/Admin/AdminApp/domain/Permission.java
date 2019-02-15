package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
@Entity
@SqlResultSetMapping(
		name="PermissionsMapping",
	    classes={
	        @ConstructorResult(
	        		targetClass=pojoPermission.class,
	            columns={
	            	@ColumnResult(name="PER_ID", type = String.class),
	                @ColumnResult(name="PER_NAME", type = String.class)
	            }
	        )
	    }
	)
@NamedNativeQuery(name="Permission.getPermissions",
query=" select p.PER_ID , p.PER_NAME from PERMISSION p",
	resultSetMapping="PermissionsMapping")

@SqlResultSetMapping(
	      name="RolePermissionMapping",
	      classes={
	          @ConstructorResult(
	                  targetClass=PermissionResult.class,
	                  columns={
	                  @ColumnResult(name="PER_ID", type = long.class),
	                  @ColumnResult(name="PER_NAME", type = String.class)   
	              }
	          )
	      }
	  )

	@NamedNativeQuery(name="Permission.getPermissionsForRole",
	      query=" select p.PER_ID ,p.PER_NAME " + 
	      		" from PERMISSION p join ROLE_PERMISSION rp on " + 
	      		" p.PER_ID =rp.PERMISSION_ID " + 
	      		" where rp.ROLE_ID = :role_id"
			, resultSetMapping="RolePermissionMapping")
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long perId;
	private String per_name;
	
	@ManyToMany(mappedBy = "Permissions")
	private Set<Role> RolesPer;
	public Set<Role> getRolesPer() {
		return RolesPer;
	}

	public void setRolesPer(Set<Role> rolesPer) {
		RolesPer = rolesPer;
	}
	public Permission() {}
	public Permission(String per_name, Set<Role> rolesPer) {
		super();
		this.per_name = per_name;
		RolesPer = rolesPer;
	}

	public Permission(String per_name) {
		super();
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
