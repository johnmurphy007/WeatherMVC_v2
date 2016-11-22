package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Comment can use at the Service Layer.
 * Activities are:
 * 	List<CommentFlag> findByAdminreviewedIsFalse(); 		Returns a list of CommentFlag objects where 'Adminreviewed' is false.
 *  save(CommentFlag commentFlagToUpdate);      			Saves object to repository
 */

import ie.cit.group3.entity.CommentFlag;

import java.util.List;

public interface CommentFlagService {
		
	public List<CommentFlag> findByAdminreviewedIsFalse();

	public void save(CommentFlag commentFlagToUpdate);

	}
