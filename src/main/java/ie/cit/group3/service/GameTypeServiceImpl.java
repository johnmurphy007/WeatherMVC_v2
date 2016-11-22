package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a ChObject can use at the Service Layer.
 * Activities are:
 *	GameType findByGamename(String gamename);
	GameType findByGamenameLike(String gamename);
	List<GameType> findAll();
	GameType findOne(int id);
	void save(GameType gt);
 */




import java.util.List;

import ie.cit.group3.entity.GameType;
import ie.cit.group3.repository.GameTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameTypeServiceImpl implements GameTypeService{
		
	@Autowired
	GameTypeRepository repo;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public GameTypeServiceImpl (GameTypeRepository repo)
	{
		this.repo = repo;
	}

	public GameTypeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public GameType findByGamename(String gamename) {
		// TODO Auto-generated method stub
		return repo.findByGamename(gamename);
	}

	@Override
	public GameType findByGamenameLike(String gamename) {
		// TODO Auto-generated method stub
		return repo.findByGamenameLike(gamename);
	}

	@Override
	public List<GameType> findAll() {
		// TODO Auto-generated method stub
		return (List<GameType>) repo.findAll();
	}

	@Override
	public GameType findOne(int id) {
		// TODO Auto-generated method stub
		return repo.findOne(id);
	}

	@Override
	public void save(GameType gt) {
		// TODO Auto-generated method stub
		repo.save(gt);
	}

	
	
	}
