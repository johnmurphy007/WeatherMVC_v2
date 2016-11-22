package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate PagingAndSortingRepository to give access to CommentRepository.
 *
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment,Integer> {
	//PagingAndSortingRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding & Paging/Sorting methods.
	
//	Specifying a SQL native query to run (as convention over coding was not giving the desired results).
	@Query(value="SELECT * FROM Comment WHERE chobject_id = :id AND flag = false", nativeQuery = true)
	public List<Comment> findBychobject_idANDFlagFalse(@Param("id") String id);

//	Specifying a SQL native query to run (as convention over coding was not giving the desired results).
	@Query(value="SELECT * FROM Comment WHERE chobject_id = :id", nativeQuery = true)
	public List<Comment> findBychobject_id(@Param("id") String id);

	public Page<Comment> findByCommenttextLike(String commenttext, Pageable pageable);
	
	long countByCommenttextLike(String commenttext);
}
