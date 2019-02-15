package com.Admin.AdminApp.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Admin.AdminApp.domain.*;

@RestController
public class RolesController {
	@Autowired 
	private RoleRepository rolerepository;
	
	@Autowired
	private PermissionRepository perRepository;

//	@RequestMapping("/rolesonhp")
//	public Set<RolesOnHP> getRolesOnHP(){
//		return rolerepository.getRolesOnHP();	 
//	}
//	
//	@RequestMapping("/permissions/{role_id}")
//    ArrayList<PermissionResult> getPermissionsForRole(@PathVariable("role_id") BigInteger role_id)
//    {
//  	  return perRepository.getPermissionsForRole(role_id);
//    }
//	
	
	 @RequestMapping("/rolesWithPermissions")
     public ArrayList<RoleResultWithPermissions> getRoles()
     {
   	 ArrayList<RoleResultWithPermissions> res = new ArrayList<RoleResultWithPermissions>();
   	 
   	 ArrayList<Role> roles = (ArrayList<Role>) rolerepository.findAll();
   	 for(Role role : roles)
   	 {
   		 ArrayList<PermissionResult> permissions = perRepository.getPermissionsForRole(role.getRoleId());
   		 RoleResultWithPermissions r = new RoleResultWithPermissions(role.getRoleId() ,role.getRole_name() ,role.getDescription() ,permissions);
   		 res.add(r);
   	 }
   	 return res;
     }
	
	 @RequestMapping("/roles")
	 public Set<RoleResult> getAllRoles() {
			return rolerepository.getAllRoles();
		}
	 
	 @RequestMapping(value = "/addRole", method = RequestMethod.POST)
	 public String adds(@RequestBody RoleResultWithPermissions rp) {
		 Role role=new Role();
		 BigInteger currentId = Role.getId();
		 currentId=currentId.add(new BigInteger("1"));
		 Role.setId(currentId);
		 role.setRoleId(currentId);
		 role.setRole_name(rp.getRole_name());
		 role.setDescription(rp.getDescription());
		 Set<Permission> permissionSet=new HashSet<Permission>();
		 Permission p;
		 ArrayList<PermissionResult> permissionList=rp.getPermissions();
		 for(PermissionResult permission1 : permissionList)
		 {
			 p=new Permission();
			 p.setPerId(permission1.getPerId());
			 p.setPer_name(permission1.getPer_name());
			 permissionSet.add(p);
		 }
		 role.setPermissions(permissionSet);
		 rolerepository.save(role);
		 return "role added";
	 }
	 
	 @RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.DELETE)
	 public String delete(@PathVariable BigInteger id) {
		 try {
			 rolerepository.deleteById(id);
			 BigInteger currentId = Role.getId();
			 currentId = currentId.subtract(new BigInteger("1"));
			 Role.setId(currentId);
		 }
		 catch(Exception e) {
			 return e.getMessage();
		 }
		 return "Role Deleted";
	 }
	 
	 @RequestMapping(value = "/updateRole/{id}", method = RequestMethod.PUT)
     public String updateRole(@PathVariable("id") BigInteger id, @RequestBody Role role) {
        Optional<Role> uOp= rolerepository.findById(id); 
        if(uOp.isPresent())
        {
       	 
       	 Role role1=uOp.get();
       	 role1.setRole_name(role.getRole_name());
       	 role1.setDescription(role.getDescription());
       	 role1.setPermissions(role.getPermissions());
       	rolerepository.save(role1);
        }
        else
       	 return "There is no role with this id";
     
        return "OK";
     }
}















