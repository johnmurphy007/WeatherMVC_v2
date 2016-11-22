package ie.cit.group3.service;

import ie.cit.group3.domain.Authorities;
import ie.cit.group3.domain.ChObject;
import ie.cit.group3.repository.AuthoritiesRepository;
import ie.cit.group3.repository.ChObjectRepository;
import ie.cit.group3.utility.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author John Murphy
 * 
 * This class implements the interface for AuthoritiesService.  
 * 
 * This class passes directly through to the Repository layer.
 * 
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 	findDistincAuthorities	Used to list the distinct Authorities that are in the repository
 */


//Identify this class as Service (Spring will detect it during @ComponentScan & create a bean of this type).
@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	//instance variable that is updated via constructor DI
	AuthoritiesRepository authoritiesRepository;

	//Autowire this object, using constructor DI.
	@Autowired
	public AuthoritiesServiceImpl (AuthoritiesRepository authoritiesRepository)
	{
		this.authoritiesRepository = authoritiesRepository;
	}
	
	
	@Override
	public Authorities get(String id) {
		// TODO Gets an Authorities object based on an id
		return authoritiesRepository.get(id);
	}

	@Override
	public void save(Authorities authority) {
		// TODO Saves an authority to repo
		authoritiesRepository.save(authority);
	}

	@Override
	public void remove(Authorities authority) {
		// TODO removes an authority from repo
		authoritiesRepository.remove(authority);
	}

	@Override
	public List<Authorities> findDistinctAuthorities() {
		// TODO Lists all distinct Authorities
		return authoritiesRepository.findDistinctAuthorities();
	}


	@Override
	public List<Authorities> findAll() {
		// TODO Lists all Authorities
		return authoritiesRepository.findAll();
	}

}
