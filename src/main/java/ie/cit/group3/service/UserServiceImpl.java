package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Users can use at the Service Layer.
 * Activities are:
 * 	Users findByUsername(String username);
	List<Users> findAll();
	void save(Users u);
	Users findOne(int id);
 */



import java.util.List;

import ie.cit.group3.entity.Users;
import ie.cit.group3.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
		
	@Autowired
	UserRepository repo;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public UserServiceImpl (UserRepository repo)
	{
		this.repo = repo;
	}

	@Override
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void save(Users u) {
		// TODO Auto-generated method stub
		repo.save(u);
	}

	@Override
	public Users findOne(int id) {
		// TODO Auto-generated method stub
		return repo.findOne(id);
	}

	
	}
