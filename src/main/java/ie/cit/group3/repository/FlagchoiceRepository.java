package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.Flagchoice;

import org.springframework.data.repository.CrudRepository;
/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to FlagchoiceRepository.
 *
 */
public interface FlagchoiceRepository extends CrudRepository<Flagchoice, Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.


	public List<Flagchoice> findAll();
	
	
}
