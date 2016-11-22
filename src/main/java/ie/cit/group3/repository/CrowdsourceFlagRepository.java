package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.CrowdsourcingFlag;

import org.springframework.data.repository.CrudRepository;
/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to CrowdsourceFlagRepository.
 *
 */
public interface CrowdsourceFlagRepository extends CrudRepository<CrowdsourcingFlag,Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.
	
	public List<CrowdsourcingFlag> findByAdminreviewedIsFalse();
	
}
