package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a CommentThumbService can use at the Service Layer.
 * Activities are:
 * 	CommentThumbServiceImpl(CommentThumbRepository repo) 
 */



import ie.cit.group3.entity.CommentThumb;
import ie.cit.group3.repository.CommentThumbRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentThumbServiceImpl implements CommentThumbService{


	@Autowired
	CommentThumbRepository repo;
	
	public CommentThumbServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommentThumbServiceImpl(CommentThumbRepository repo) {
		this.repo = repo;
	}


	@Override
	public void save(CommentThumb commentThumb) {
		// TODO Auto-generated method stub
		repo.save(commentThumb);
	}
	
	
	}
