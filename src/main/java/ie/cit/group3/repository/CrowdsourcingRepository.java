package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.Crowdsourcing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
* @author john murphy
* 
* This interface inherits the JPA/Hibernate PagingAndSortingRepository to give access to CrowdsourcingRepository.
*
*/
public interface CrowdsourcingRepository extends PagingAndSortingRepository<Crowdsourcing, Integer> {
//PagingAndSortingRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding & Paging/Sorting methods.
	
//	Specifying a SQL native query to run (as convention over coding was not giving the desired results).
	@Query(value="SELECT * FROM Crowdsourcing WHERE chobject_id = :id", nativeQuery = true)
	public List<Crowdsourcing> findBychobject_id(@Param("id") String id);

//	Specifying a SQL native query to run (as convention over coding was not giving the desired results).
	@Query(value="SELECT * FROM Crowdsourcing WHERE chobject_id = :id AND flag = false", nativeQuery = true)
	public List<Crowdsourcing> findBychobject_idANDflagFalse(@Param("id") String id);

	public Page<Crowdsourcing> findByDescriptionLike(String description, Pageable pageable);

	long countByDescriptionLike(String description);
}
