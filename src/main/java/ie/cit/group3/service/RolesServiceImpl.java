package ie.cit.group3.service;

import ie.cit.group3.domain.Role;
import ie.cit.group3.repository.RolesRepository;
import ie.cit.group3.utility.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author John Murphy
 * 
 * This class implements the interface for RoleService.  
 * 
 * This class passes directly through to the Repository layer.
 * 
 * Activities it must implement are:
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


//Identify this class as Service (Spring will detect it during @ComponentScan & create a bean of this type).
@Service
public class RolesServiceImpl implements RolesService {

	//instance variable that is updated via constructor DI
	private RolesRepository rolesRepository;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public RolesServiceImpl (RolesRepository rolesRepository)
	{
		this.rolesRepository = rolesRepository;
	}
	
	@Override
	public Role get(String id) 
	{
		return rolesRepository.get(id);
	}

	@Override
	public void save(Role role) 
	{
		rolesRepository.save(role);
	}

	@Override
	public void remove(Role role) 
	{
		rolesRepository.remove(role);
	}

	@Override
	public List<Role> findAll() 
	{
		return rolesRepository.findAll();
	}

	@Override
	public int CountRole() {
		// TODO Auto-generated method stub
		return rolesRepository.CountRole();
	}

	@Override
	public List<Role> findByRoleId(String searchrole) {
		// TODO Auto-generated method stub
		return rolesRepository.findByRoleId(searchrole);
	}

	@Override
	public Page<Role> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return rolesRepository.findAll(pageNo, pageSize);
	}

	@Override
	public long countByRoleId(String id) {
		// TODO Auto-generated method stub
		return rolesRepository.countByRoleId(id);
	}

	@Override
	public int CountAll() {
		// TODO Auto-generated method stub
		return rolesRepository.CountAll();
	}
}
