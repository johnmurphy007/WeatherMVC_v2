package ie.cit.group3.repository;

import ie.cit.group3.entity.CommentThumb;

import org.springframework.data.repository.CrudRepository;

/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to CommentThumbRepository.
 *
 */
public interface CommentThumbRepository extends CrudRepository<CommentThumb,Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.
	
	
}
