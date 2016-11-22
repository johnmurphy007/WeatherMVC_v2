package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a GameType can use at the Service Layer.
 * Activities are:
 * 	GameType findByGamename(String gamename);
	GameType findByGamenameLike(String gamename);
	List<GameType> findAll();
	GameType findOne(int id);
	void save(GameType gt);

 */


import java.util.List;

import ie.cit.group3.entity.GameType;




public interface GameTypeService {
		
	public GameType findByGamename(String gamename);
	
	public GameType findByGamenameLike(String gamename);

	public List<GameType> findAll();

	public GameType findOne(int id);

	public void save(GameType gt);

	}
