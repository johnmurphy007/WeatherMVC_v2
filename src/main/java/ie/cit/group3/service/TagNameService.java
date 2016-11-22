package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a TagName can use at the Service Layer.
 * Activities are:
 *  save		Save a object/record to the repository
 * 	List<TagName> findBychobject_id(String id);	
	Page<TagName> findByTagwordLike(String tagword, Pageable pageable);
	long countByTagwordLike(String tagword);

 */




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ie.cit.group3.entity.TagName;




public interface TagNameService {
		
//	@Query(value="SELECT * FROM TagName WHERE chobject_id = :id", nativeQuery = true)
//	public List<TagName> findBychobject_id(@Param("id") String id);	
	public List<TagName> findBychobject_id(String id);	
	
	public Page<TagName> findByTagwordLike(String tagword, Pageable pageable);
	long countByTagwordLike(String tagword);

	public void save(TagName tagname);
	
	}
