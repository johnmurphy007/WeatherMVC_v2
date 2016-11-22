package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Flagchoice can use at the Service Layer.
 * Activities are:
 * 		List<Flagchoice> findAll()
 */



import ie.cit.group3.entity.Flagchoice;
import ie.cit.group3.repository.FlagchoiceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlagchoiceServiceImpl implements FlagchoiceService{
		
	@Autowired
	FlagchoiceRepository repo;
	
	public FlagchoiceServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Autowire this object, using constructor DI.
	@Autowired
	public FlagchoiceServiceImpl (FlagchoiceRepository repo)
	{
		this.repo = repo;
	}

	@Override
	public List<Flagchoice> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}




	

	
	}
