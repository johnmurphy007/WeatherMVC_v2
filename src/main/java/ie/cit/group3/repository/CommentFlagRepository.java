package ie.cit.group3.repository;

import java.util.List;
import ie.cit.group3.entity.CommentFlag;

import org.springframework.data.repository.CrudRepository;


/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to CommentFlag repository.
 *
 */
public interface CommentFlagRepository extends CrudRepository<CommentFlag,Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.
	

	public List<CommentFlag> findByAdminreviewedIsFalse();
}
