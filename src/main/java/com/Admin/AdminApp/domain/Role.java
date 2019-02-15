package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
//@SqlResultSetMapping(
//		name="RolesOnHPMapping",
//	    classes={
//	        @ConstructorResult(
//	        		targetClass=RolesOnHP.class,
//	            columns={
//	                @ColumnResult(name="ROLE_NAME", type = String.class),
//	                @ColumnResult(name="DESCRIPTION", type = String.class),
//	                @ColumnResult(name="PER_NAME", type = String.class),
//	                @ColumnResult(name="ASSIGNED", type = String.class)
//
//	            }
//	        )
//	    }
//	)
//@NamedNativeQuery(name="Role.getRolesOnHP",
//query=" select r.ROLE_NAME ,r.DESCRIPTION  , (SELECT STRING_AGG(p.PER_NAME , ' / ' ) "+
//" from ROLE r1 join ROLE_PERMISSION pr on r1.ROLE_ID =pr.ROLE_ID "+
//" join PERMISSION p on p.PER_ID =pr.PERMISSION_ID "+
//  " where r1.ROLE_ID =r.ROLE_ID ) as PER_NAME,count(r.ROLE_NAME ) as ASSIGNED "+
//" from ROLE r "+
// " join ROLE_PERMISSION pr on r.ROLE_ID =pr.ROLE_ID "+
// " join PERMISSION p on p.PER_ID =pr.PERMISSION_ID "+
//" group by  r.ROLE_NAME ,r.ROLE_ID, r.DESCRIPTION ",
//	resultSetMapping="RolesOnHPMapping")

@SqlResultSetMapping(
	      name="allRoleMapping",
	      classes={
	          @ConstructorResult(
	                  targetClass=RoleResult.class,
	                  columns={
	                  @ColumnResult(name="role_id", type = BigInteger.class),
	                  @ColumnResult(name="role_name", type = String.class),
	                  @ColumnResult(name="description", type = String.class),
	                  
	                   
	              }
	          )
	      }
	  )

    @NamedNativeQuery(name="Role.getAllRoles",
     query="SELECT ROLE.role_id, ROLE.description, ROLE.role_name "+
		 " FROM ROLE " , resultSetMapping="allRoleMapping")


@SqlResultSetMapping(
	      name="RoleMapping",
	      classes={
	          @ConstructorResult(
	                  targetClass=RoleResult.class,
	                  columns={
	                  @ColumnResult(name="role_id", type = BigInteger.class),
	                  @ColumnResult(name="role_name", type = String.class),
	                  @ColumnResult(name="description", type = String.class),
	                  
	                   
	              }
	          )
	      }
	  )

    @NamedNativeQuery(name="Role.getRoles",
     query="SELECT ROLE.role_id, ROLE.description, ROLE.role_name "+
		 " FROM USERS " +
         " INNER JOIN USER_ROLE ON USERS.USER_ID=USER_ROLE.User_Id " +
         " INNER JOIN ROLE ON ROLE.ROLE_ID=USER_ROLE.ROLE_ID " +
         " WHERE((USERS.USER_ID)=:user_id) " , resultSetMapping="RoleMapping")

public class Role {
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger RoleId;
	@Column(unique=true)
	private String Role_name;
	private String Description;
	private static BigInteger id1= new BigInteger("0");

	@ManyToMany(mappedBy = "roles")
	private Set<Users> users;
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	 @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "Role_Id", referencedColumnName = "RoleId"),
    inverseJoinColumns = @JoinColumn(name = "permission_Id", referencedColumnName = "perId"))
    private Set<Permission> Permissions;
	public Set<Permission> getPermissions() {
		return Permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		Permissions = permissions;
	}

	public Role() {
		super();
	}
    

	public Role(String role_name, String description, Set<Permission> permissions) {
		id1=id1.add(new BigInteger("1"));
		this.RoleId=id1;
		Role_name = role_name;
		Description = description;
		Permissions = permissions;
	}

	public Role(String role_name, String description) {
		id1=id1.add(new BigInteger("1"));
		this.RoleId=id1;
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

	public static BigInteger getId() {
		return id1;
	}

	public static void setId(BigInteger id) {
		Role.id1 = id;
	}
	


}
