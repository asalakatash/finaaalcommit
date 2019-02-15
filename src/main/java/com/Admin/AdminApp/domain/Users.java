package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@SqlResultSetMapping(
//        name="usersOnHPMapping",
//        classes={
//            @ConstructorResult(
//                    targetClass=UsersOnHP.class,
//                columns={
//                    @ColumnResult(name="NAME", type = String.class),
//                    @ColumnResult(name="PHONE", type = String.class),
//                    @ColumnResult(name="ROLE_NAME", type = String.class),
//                    @ColumnResult(name="LOGIN", type = String.class)
//
//                }
//            )
//        }
//    )
// @NamedNativeQuery(name="Users.getUsersOnHP",
//        query="SELECT USERS.NAME , USERS.PHONE, ROLE.ROLE_NAME, USERS.LOGIN " +
//         " FROM USERS " +
//         "INNER JOIN USER_ROLE ON USERS.USER_ID=USER_ROLE.ID " +
//         "INNER JOIN ROLE ON ROLE.ROLE_ID=USER_ROLE.ROLE_ID",
//         resultSetMapping="usersOnHPMapping")
//
//@SqlResultSetMapping(
//        name="UsersRoleArrayMapping",
//        classes={
//            @ConstructorResult(
//                    targetClass=UserRoleArray.class,
//                columns={
//                    @ColumnResult(name="ROLE_NAME", type = String.class)
//
//                }
//            )
//        }
//    )
// @NamedNativeQuery(name="Users.getUsersRoleArray",
//        query="SELECT ROLE.ROLE_NAME " +
//         " FROM USERS " +
//         " INNER JOIN USER_ROLE ON USERS.USER_ID=USER_ROLE.ID " +
//         " INNER JOIN ROLE ON ROLE.ROLE_ID=USER_ROLE.ROLE_ID " +
//         " WHERE((USERS.USER_ID)=:UserId) ",resultSetMapping="UsersRoleArrayMapping")
//
//@SqlResultSetMapping(
//        name="UsersWithRoleMapping",
//        classes={
//            @ConstructorResult(
//                    targetClass=UsersWithRole.class,
//                columns={
//                    @ColumnResult(name="NAME", type = String.class),
//                    @ColumnResult(name="PHONE", type = String.class),
//                    @ColumnResult(name="ROLESALL", type = String.class),
//                    @ColumnResult(name="LOGIN", type = String.class)
//                }
//            )
//        }
//    )
//@NamedNativeQuery(name="Users.getUsersWithRole",
//query=" SELECT u.NAME , u.PHONE , (SELECT STRING_AGG(ROLE.ROLE_NAME , ' / ' ) " +
//        " from USERS join USER_ROLE on USERS.USER_ID =USER_ROLE.User_Id " +
//        " join ROLE on ROLE.ROLE_ID =USER_ROLE.ROLE_ID " +
//        "  where USERS.USER_ID =u.USER_ID ) as ROLESALL ,u.LOGIN " +
//        " from USERS u " +
//        " join USER_ROLE ur on u.USER_ID =ur.User_Id " +
//        "  join ROLE ro on ro.ROLE_ID =ur.ROLE_ID " +
//        " group by  u.NAME ,u.USER_ID, u.PHONE , u.LOGIN ",
//    resultSetMapping="UsersWithRoleMapping")


                  //////////////////////////////////////////////////////////////////////


@SqlResultSetMapping(
        name="loginMapping",
        classes={
            @ConstructorResult(
                    targetClass=Users.class,
                columns={
                    @ColumnResult(name="USER_ID", type = long.class),
                    @ColumnResult(name="NAME", type = String.class),
                    @ColumnResult(name="EMAIL", type = String.class),
                    @ColumnResult(name="PHONE", type = String.class),
                    @ColumnResult(name="PASSWORD", type = String.class),
                    @ColumnResult(name="LOGIN", type = String.class)
                }
            )
        }
    )
@NamedNativeQuery(name = "Users.login", query = " SELECT USERS.USER_ID , USERS.NAME "
		+ ", USERS.EMAIL , USERS.PHONE , USERS.PASSWORD , USERS.LOGIN "
		+ " from USERS "
		+ " WHERE USERS.EMAIL =:Email AND "
		+ " USERS.PASSWORD =:Password ", resultSetMapping = "loginMapping")

///////////////////////////////////////
@SqlResultSetMapping(
        name="UserPermissionsMapping",
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
@NamedNativeQuery(name = "Users.getPermissionsForUser",
         query = " select distinct p.PER_ID ,p.PER_NAME "+
        		" from PERMISSION p join ROLE_PERMISSION rp on p.PER_ID = rp.PERMISSION_ID "+
        		" JOIN  ROLE r on r.ROLE_ID =rp.ROLE_ID "+
        		 " JOIN USER_ROLE  ur ON r.ROLE_ID=ur.ROLE_ID "+
        		" JOIN USERS u on u.USER_ID=ur.USER_ID "+
        		" where ((u.USER_ID)=:user_id) "
        		 , resultSetMapping = "UserPermissionsMapping")


public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long UserId;
    private String Name;
    @Column(unique=true)
    private String Email;
    private String Phone;
    private String Password;
    private String login;
   // private static long counter=0;

     @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
   @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "User_Id", referencedColumnName = "UserId"),
   inverseJoinColumns = @JoinColumn(name = "Role_id", referencedColumnName = "RoleId"))
     @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
     private Set<Role> roles;
   public Set<Role> getRoles() {
        return roles;
    }
   public void setRoles(Set<Role> roles) {
	   this.roles = roles;
   }
   public Users() {
	   super();
   }

   public long getUserId() {
	   return UserId;
   }
    public void setUserId(long userId) {
        UserId = userId;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public Users(String name, String email, String phone, String password, String login) {
        super();
//        counter++;
//        this.UserId = counter;
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Password = password;
        this.login = login;
    }
    public Users(String name, String email, String phone, String password, String login, Set<Role> roles) {
        super();
//        counter++;
//        this.UserId = counter;
        this.Name = name;
        this.Email = email;
        this.Phone = phone;
        this.Password = password;
        this.login = login;
        this.roles = roles;
    }
//	public static long getCounter() {
//		return counter;
//	}
//	public static void setCounter(long counter) {
//		Users.counter = counter;
//	}
    
    
    
}

