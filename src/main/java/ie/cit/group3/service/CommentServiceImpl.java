package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Comment Object can use at the Service Layer.
 * Activities are:
 * 	List<Comment> findBychobject_idANDFlagFalse(String chobject_id);
	List<Comment> findBychobject_id(String chobject_id);
	Page<Comment> findByCommenttextLike(String commenttext, Pageable pageable);
	long countByCommenttextLike(String commenttext);
	public Comment findOne(int id);
	public void save(Comment comment);
 * 
 */

import ie.cit.group3.entity.Comment;
import ie.cit.group3.entity.CommentFlag;
import ie.cit.group3.repository.CommentFlagRepository;
import ie.cit.group3.repository.CommentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
		
	@Autowired
	CommentRepository repo;
	
	
	public CommentServiceImpl() {
		super();
		// TODO empty constructor
	}


	public CommentServiceImpl(CommentRepository repo) {
		// TODO constructor with one @param CommentRepository
		this.repo = repo;
	}


	@Override
	public List<Comment> findBychobject_idANDFlagFalse(String id) {
		// TODO method that returns a list of comments based on chobject_id and flag = false
		return repo.findBychobject_idANDFlagFalse(id);
	}


	@Override
	public List<Comment> findBychobject_id(String id) {
		// TODO method that returns a list of comments based on chobject_id
		return repo.findBychobject_id(id);
	}


	@Override
	public Page<Comment> findByCommenttextLike(String commenttext,
			Pageable pageable) {
		// TODO method that returns a Page of comments based on commenttext argument
		return repo.findByCommenttextLike(commenttext, pageable);
	}


	@Override
	public long countByCommenttextLike(String commenttext) {
		// TODO method that returns a count of the commenttext items that are like the argument passed in.
		return repo.countByCommenttextLike(commenttext);
	}


	@Override
	public Comment findOne(int id) {
		// TODO method that finds a comment based on its id
		return repo.findOne(id);
	}


	@Override
	public void save(Comment comment) {
		// TODO method that saves Comment object to repo
		repo.save(comment);
	}

	
	}
