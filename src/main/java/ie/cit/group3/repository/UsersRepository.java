package ie.cit.group3.repository;

import ie.cit.group3.domain.User;

import java.util.List;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Participant can use on a Repository.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */

public interface UsersRepository {
	
	public User get (String id);
	
	public void save(User user);
	
	public void remove(User user);
	
	public List<User> findAll();

}
