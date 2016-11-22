package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.Gamification;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to GamificationRepository.
 *
 */
public interface GamificationRepository extends CrudRepository<Gamification, Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.

	public List<Gamification> findByUser_id(int user_id);
	
	
	//There is a bug in MYSQL JDBC https://forum.hibernate.org/viewtopic.php?f=1&t=1006399
	@Query(value="SELECT user_id AS id, SUM(gamepoints) AS gamepoints, user_id, user_id AS chobject_id, Date(2015-05-05) AS datestamp, '18' AS game_id FROM Gamification GROUP BY user_id ORDER BY Sum(gamepoints) DESC LIMIT 10", nativeQuery = true)
	public List<Gamification> findTopTen();

//	Specifying a SQL native query to run (as convention over coding was not giving the desired results).
	@Query(value="SELECT Count(*) FROM Gamification WHERE user_id = :user_id", nativeQuery = true)
	public int countByGamificationByUser(@Param("user_id") int user_id);

//	Specifying a SQL native query to run (as convention over coding was not giving the desired results).
	@Query(value="SELECT SUM(gamepoints) FROM Gamification WHERE user_id = :user_id", nativeQuery = true)
	public int findBySumGamePointsByUser(@Param("user_id") int user_id);
	
	//There is a bug in MYSQL JDBC https://forum.hibernate.org/viewtopic.php?f=1&t=1006399
	@Query(value="SELECT user_id AS id, SUM(gamepoints) AS gamepoints, user_id, user_id AS chobject_id, Date(2015-05-05) AS datestamp, '18' AS game_id FROM Gamification GROUP BY user_id ORDER BY Sum(gamepoints) DESC", nativeQuery = true)
	public List<Gamification> findAllUsersPositions();
	
}
