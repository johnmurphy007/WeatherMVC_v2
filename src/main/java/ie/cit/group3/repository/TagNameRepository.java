package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.TagName;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
/**
* @author john murphy
* 
* This interface inherits the JPA/Hibernate PagingAndSortingRepository to give access to TagNameRepository.
*
*/
public interface TagNameRepository extends PagingAndSortingRepository<TagName,Integer> {
	//PagingAndSortingRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding & Paging/Sorting methods.
	
	@Query(value="SELECT * FROM TagName WHERE chobject_id = :id", nativeQuery = true)
	public List<TagName> findBychobject_id(@Param("id") String id);	
	
	public Page<TagName> findByTagwordLike(String tagword, Pageable pageable);
	
	long countByTagwordLike(String tagword);
}
