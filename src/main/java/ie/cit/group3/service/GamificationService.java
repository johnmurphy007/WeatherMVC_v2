package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a Gamification can use at the Service Layer.
 * Activities are:
 * 		List<Gamification> findTopTen();
		int countByGamificationByUser(int user_id);
		int findBySumGamePointsByUser(int user_id);
		List<Gamification> findAllUsersPositions();
		void save(Gamification g);
 * 
 */


import java.util.List;

import ie.cit.group3.entity.Gamification;




public interface GamificationService {
		
	public List<Gamification> findByUser_id(int user_id);
	
	//There is a bug in MYSQL JDBC https://forum.hibernate.org/viewtopic.php?f=1&t=1006399
//	@Query(value="SELECT user_id AS id, SUM(gamepoints) AS gamepoints, user_id, user_id AS chobject_id, Date(2015-05-05) AS datestamp, '18' AS game_id FROM Gamification GROUP BY user_id ORDER BY Sum(gamepoints) DESC LIMIT 10", nativeQuery = true)
	public List<Gamification> findTopTen();
	
//	@Query(value="SELECT Count(*) FROM Gamification WHERE user_id = :user_id", nativeQuery = true)
//	public int countByGamificationByUser(@Param("user_id") int user_id);
	public int countByGamificationByUser(int user_id);

//	@Query(value="SELECT SUM(gamepoints) FROM Gamification WHERE user_id = :user_id", nativeQuery = true)
//	public int findBySumGamePointsByUser(@Param("user_id") int user_id);
	public int findBySumGamePointsByUser(int user_id);
	
	//There is a bug in MYSQL JDBC https://forum.hibernate.org/viewtopic.php?f=1&t=1006399
//	@Query(value="SELECT user_id AS id, SUM(gamepoints) AS gamepoints, user_id, user_id AS chobject_id, Date(2015-05-05) AS datestamp, '18' AS game_id FROM Gamification GROUP BY user_id ORDER BY Sum(gamepoints) DESC", nativeQuery = true)
	public List<Gamification> findAllUsersPositions();

	public void save(Gamification g);
	}
