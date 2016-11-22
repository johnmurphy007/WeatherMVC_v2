package ie.cit.group3.service;



import ie.cit.group3.domain.Role;
import ie.cit.group3.utility.Page;

import java.util.List;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Role can use at the Service Layer.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 *  int CountRole();
 	List<Role> findByRoleId(String searchrole);
	Page<Role> findAll(int pageNo, int pageSize);
	long countByRoleId(String id);
	int CountAll();
 */

public interface RolesService {
	
	public Role get (String id);
	
	public void save(Role role);
	
	public void remove(Role role);
	
	public List<Role> findAll();
	
	public int CountRole();

	public List<Role> findByRoleId(String searchrole);

	public Page<Role> findAll(int pageNo, int pageSize);
	
	public long countByRoleId(String id);

	public int CountAll();

}
