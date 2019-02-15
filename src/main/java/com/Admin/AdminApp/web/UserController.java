package com.Admin.AdminApp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Admin.AdminApp.domain.LogIn;
import com.Admin.AdminApp.domain.Permission;
import com.Admin.AdminApp.domain.PermissionRepository;
import com.Admin.AdminApp.domain.PermissionResult;
import com.Admin.AdminApp.domain.Role;
import com.Admin.AdminApp.domain.RoleRepository;
import com.Admin.AdminApp.domain.RoleResult;
import com.Admin.AdminApp.domain.RolesOnHP;
import com.Admin.AdminApp.domain.UserRepository;
import com.Admin.AdminApp.domain.UserResult;
import com.Admin.AdminApp.domain.UserRoleArray;
import com.Admin.AdminApp.domain.Users;
import com.Admin.AdminApp.domain.UsersOnHP;
import com.Admin.AdminApp.domain.UsersRoleArray;
import com.Admin.AdminApp.domain.UsersWithRole;





@RestController
public class UserController {
	@Autowired 
	private UserRepository repository;
	@Autowired 
	private PermissionRepository prepository;
	@Autowired 
	private RoleRepository rolerepository;
//
//	  @RequestMapping("/usersonhp")
//	  public Set<UsersOnHP> getUsersOnHP() 
//	  {
//         return repository.getUsersOnHP();
//      }
////      @RequestMapping("/users")
////      public Iterable<Users> getUsers() {
////        return repository.findAll();
////      }
//      
//      @RequestMapping("/userrole/{id}")
//      public Set<UserRoleArray> getUsersRoleArray(@PathVariable long id){
//    	  return repository.getUsersRoleArray(id);
//      }
//      
//      @RequestMapping("/userswithrole")
//      public Set<UsersWithRole> getUsersWithRole() {
//        return repository.getUsersWithRole();
//      }
//      /////////////////////////////////////////////////////////////////////////////////////////
      @RequestMapping("/users")
      public ArrayList<UserResult> getUsers()
      {
    	 ArrayList<UserResult> res = new ArrayList<UserResult>();
    	 
    	 ArrayList<Users> users = (ArrayList<Users>) repository.findAll();
    	 for(Users user : users)
    	 {
    		 ArrayList<RoleResult> roles = rolerepository.getRoles(user.getUserId());
   		    UserResult u = new UserResult(user.getUserId(), user.getEmail(), user.getName(), user.getPassword(), user.getPhone(), user.getLogin(),roles);
    		 res.add(u);
    	 }
    	 return res;
      }
      
      @RequestMapping("/roles/{user_id}")
      ArrayList<RoleResult> getRoles(@PathVariable("user_id") long user_id)
      {
    	  return rolerepository.getRoles(user_id);
    	 
      }
      @RequestMapping("/permissions/{user_id}")
      ArrayList<PermissionResult> getPermissionsForUser(@PathVariable("user_id") long user_id)
      {
    	  return repository.getPermissionsForUser(user_id);
    	 
      }
      
//      @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//      public void adds(@RequestBody Users user) {
//    	  
//      repository.save(user);
//      }
      @RequestMapping(value = "/addUser", method = RequestMethod.POST)
      public String adds(@RequestBody UserResult u) {
    	  Users user=new Users();
//    	  long currentID = Users.getCounter();
//    	  currentID = currentID + 1;
    	  user.setUserId(u.getId());
    	  user.setName(u.getName());
    	  user.setPhone(u.getPhone());
    	  user.setEmail(u.getEmail());
    	  user.setPassword(u.getPassword());
    	  user.setLogin(u.getLogin());
    	  Set<Role> roleSet=new HashSet<Role>();
    	  Role r;
    	  ArrayList<RoleResult> roleList=u.getRoles();
    	  for(RoleResult role1 : roleList)
    	  {
    		  r=new Role();
    		  r.setRole_name(role1.getRole_name());
    		  r.setDescription(role1.getDescription());
    		  r.setRoleId(role1.getRoleId());   
    		  roleSet.add(r);
    	  }
    	user.setRoles(roleSet);
    	repository.save(user);
    	return "user added";
      }
      @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
      public String delete(@PathVariable long id) {
    	  try {
    		  repository.deleteById(id);
//    		  long currentID = Users.getCounter();
//        	  currentID = currentID - 1;
//        	  Users.setCounter(currentID);
    	  }
    	  catch(Exception e) {
    		  return e.getMessage(); }
    	  return "User Deleted";
      }
      
      
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") Long id, @RequestBody UserResult user) {
       Optional<Users> uOp= repository.findById(id); 
       if(uOp.isPresent())
       {
      	 
      	 Users user1=uOp.get();
      	  Set<Role> roleSet=new HashSet<Role>();
    	  Role r=new Role();
    	  ArrayList<RoleResult> roleList=user.getRoles();
    	  for(RoleResult role1 : roleList)
    	  {
    		  r.setRole_name(role1.getRole_name());
    		  r.setDescription(role1.getDescription());
    		  r.setRoleId(role1.getRoleId());   
    		  roleSet.add(r);
    	  }
       	 user1.setRoles(roleSet);
      	 user1.setName(user.getName());
      	 user1.setEmail(user.getEmail());
      	 user1.setPhone(user.getPhone());
      	 user1.setPassword(user.getPassword());
      	 user1.setLogin(user.getLogin());
      	 repository.save(user1);
       }
       else
      	 return "There is no user with this id";
    
       return "OK";
    }

	@RequestMapping(value = "/login")
	public String login(@RequestBody LogIn lg) {
		String email=lg.getEmail();
		String pass=lg.getPassword();
		try {
			Optional<Users> user= repository.login(email, pass);
		if(user.isPresent())
			return "Log in Success";
		else
			return "Invalid email or password";

		}
		catch(NullPointerException e)
		{
			return e.getMessage();
		}
	}
      
	} 


