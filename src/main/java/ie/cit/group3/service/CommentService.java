package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Comment object can use at the Service Layer.
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


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentService {
		
	
	
//	@Query(value="SELECT * FROM Comment WHERE chobject_id = :id AND flag = false", nativeQuery = true)
//	public List<Comment> findBychobject_idANDFlagFalse(@Param("id") String id);
	public List<Comment> findBychobject_idANDFlagFalse(String id);

//	@Query(value="SELECT * FROM Comment WHERE chobject_id = :id", nativeQuery = true)
//	public List<Comment> findBychobject_id(@Param("id") String id);
	public List<Comment> findBychobject_id(String id);

	
	public Page<Comment> findByCommenttextLike(String commenttext, Pageable pageable);
	
	long countByCommenttextLike(String commenttext);

	public Comment findOne(int id);

	public void save(Comment comment);
	}
