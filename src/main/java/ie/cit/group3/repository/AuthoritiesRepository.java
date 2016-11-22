package ie.cit.group3.repository;


import ie.cit.group3.domain.Authorities;

import java.util.List;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Authorities object can use on a Repository.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 	findDistincAuthorities	Used to list the distinct Authorities that are in the repository
 */

public interface AuthoritiesRepository {
	
	public Authorities get (String id);
	
	public void save(Authorities authority);
	
	public void remove(Authorities authority);
	
	public List<Authorities> findAll();
	
	public List<Authorities> findDistinctAuthorities();

}
