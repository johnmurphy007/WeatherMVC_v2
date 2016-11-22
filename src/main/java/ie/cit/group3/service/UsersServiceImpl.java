package ie.cit.group3.service;


import ie.cit.group3.domain.User;
import ie.cit.group3.repository.UsersRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author John Murphy
 * 
 * This class implements the interface for UsersRepository.  
 * 
 * This class passes directly through to the Repository layer.
 * 
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */


//Identify this class as Service (Spring will detect it during @ComponentScan & create a bean of this type).
@Service
public class UsersServiceImpl implements UsersService {
	
	//instance variable that is updated via constructor DI
	UsersRepository usersRepository;

	public UsersServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Autowire this object, using constructor DI.
	@Autowired
	public UsersServiceImpl (UsersRepository usersRepository)
	{
		this.usersRepository = usersRepository;
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		return usersRepository.get(id);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		usersRepository.save(user);
	}

	@Override
	public void remove(User user) {
		// TODO Auto-generated method stub
		usersRepository.remove(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}
	
	
	

}
