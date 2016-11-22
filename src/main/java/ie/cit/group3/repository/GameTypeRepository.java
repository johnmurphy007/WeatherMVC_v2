package ie.cit.group3.repository;

import ie.cit.group3.entity.GameType;

import org.springframework.data.repository.CrudRepository;
/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to GameTypeRepository.
 *
 */
public interface GameTypeRepository extends CrudRepository<GameType, Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.

	public GameType findByGamename(String gamename);
	
	public GameType findByGamenameLike(String gamename);


}
