package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that an Users object can use at the Service Layer.
 * Activities are:
 * 	Users findByUsername(String username);
	List<Users> findAll();
	void save(Users u);
	Users findOne(int id);
 * 
 */




import java.util.List;


import ie.cit.group3.entity.Users;




public interface UserService {
	
	public Users findByUsername(String username);

	public List<Users> findAll();

	public void save(Users u);

	public Users findOne(int id);
	
	}
