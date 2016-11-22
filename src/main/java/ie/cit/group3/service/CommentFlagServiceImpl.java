package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a CommentFlag can use at the Service Layer.
 * Activities are:
 * 		List<CommentFlag> findByAdminreviewedIsFalse() 
 * 		void save(CommentFlag commentFlagToUpdate) 
 */


import ie.cit.group3.entity.CommentFlag;

import ie.cit.group3.repository.CommentFlagRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CommentFlagServiceImpl implements CommentFlagService{
		
	@Autowired
	CommentFlagRepository repo;
	
	public CommentFlagServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Autowire this object, using constructor DI.
	@Autowired
	public CommentFlagServiceImpl (CommentFlagRepository repo)
	{
		this.repo = repo;
	}

	@Override
	public List<CommentFlag> findByAdminreviewedIsFalse() {
		// TODO Auto-generated method stub
		return repo.findByAdminreviewedIsFalse();
	}

	@Override
	public void save(CommentFlag commentFlagToUpdate) {
		// TODO Auto-generated method stub
		repo.save(commentFlagToUpdate);
	}
	

	
	}
