package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a ChObject can use at the Service Layer.
 * Activities are:
 * 	List<Crowdsourcing> findBychobject_id(String id);
	List<Crowdsourcing> findBychobject_idANDflagFalse(String id);
	Page<Crowdsourcing> findByDescriptionLike(String description, Pageable pageable);
	long countByDescriptionLike(String description);
	Crowdsourcing findOne(int id);
	void save(Crowdsourcing crowdsourcing);
 * 
 */

import ie.cit.group3.entity.Crowdsourcing;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CrowdsourcingService {
		
	//@Query(value="SELECT * FROM Crowdsourcing WHERE chobject_id = :id", nativeQuery = true)
	//public List<Crowdsourcing> findBychobject_id(@Param("id") String id);
	public List<Crowdsourcing> findBychobject_id(String id);

//	@Query(value="SELECT * FROM Crowdsourcing WHERE chobject_id = :id AND flag = false", nativeQuery = true)
//	public List<Crowdsourcing> findBychobject_idANDflagFalse(@Param("id") String id);
	public List<Crowdsourcing> findBychobject_idANDflagFalse(String id);

	public Page<Crowdsourcing> findByDescriptionLike(String description, Pageable pageable);
	
	long countByDescriptionLike(String description);

	public Crowdsourcing findOne(int id);

	public void save(Crowdsourcing crowdsourcing);

	}
