package com.Admin.AdminApp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Admin.AdminApp.domain.Permission;
import com.Admin.AdminApp.domain.PermissionRepository;
import com.Admin.AdminApp.domain.Role;
import com.Admin.AdminApp.domain.RoleRepository;
import com.Admin.AdminApp.domain.UserRepository;
import com.Admin.AdminApp.domain.Users;

@SpringBootApplication
public class AdminAppApplication {
	@Autowired 
    private UserRepository UserRepository ;
	@Autowired 
    private RoleRepository Rolerepository;
	@Autowired
	private PermissionRepository perRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdminAppApplication.class, args);
	}
	@Bean
    CommandLineRunner runner(){
      return args -> {
    	
    	  UserRepository.deleteAll();
    	  Rolerepository.deleteAll();
    	  perRepository.deleteAll();
    	  Permission per1 = new Permission("per1");
    	  perRepository.save(per1);
    	  Permission per2 = new Permission("per2");
    	  perRepository.save(per2);
    	  Permission per3 = new Permission("per3");
    	  perRepository.save(per3);
    	  
    	  Set<Permission> pers1= new HashSet<Permission>();
    	  pers1.add(per1);
    	  pers1.add(per2);
          Set<Permission> pers2= new HashSet<Permission>();
          pers2.add(per1);
          pers2.add(per3);
          Set<Permission> pers3= new HashSet<Permission>();
          pers3.add(per3);
          pers3.add(per2);
          //Add trip objects
          Role role1 = new Role("role1","desc1",pers1);
          Role role2 = new Role("role2","desc2",pers2);
          Role role3 = new Role("role3","desc3",pers3);
          Rolerepository.save(role1);
          Rolerepository.save(role2);
          Rolerepository.save(role3);

          // Add car object with link to owners and save these to db.
          
          Set<Role> Roles1= new HashSet<Role>();
          Roles1.add(role1);
          Roles1.add(role2);
          Set<Role> Roles2= new HashSet<Role>();
          Roles2.add(role1);
          Roles2.add(role3);
          Set<Role> Roles3= new HashSet<Role>();
          Roles3.add(role3);
          Roles3.add(role2);
    
          
          Users user1 = new Users("malik abed","malik@gmail.com","0528432254","pass1","yes",Roles1);
          UserRepository.save(user1);
          Users user2 = new Users("asala","asala@gmail.com","0528432254","pass2","No",Roles2);
          UserRepository.save(user2);
          Users user3 = new Users("tawfek","tawfek@gmail.com","0528432254","pass3","yes",Roles3);
          UserRepository.save(user3);
     };
	}

}


