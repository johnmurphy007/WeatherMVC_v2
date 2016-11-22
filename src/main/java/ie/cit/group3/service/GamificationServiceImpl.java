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
		void save(Gamification g)
 */




import java.util.List;

import ie.cit.group3.entity.Gamification;
import ie.cit.group3.repository.GamificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamificationServiceImpl implements GamificationService{
		
	@Autowired
	GamificationRepository repo;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public GamificationServiceImpl (GamificationRepository repo)
	{
		this.repo = repo;
	}

	public GamificationServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Gamification> findByUser_id(int user_id) {
		// TODO Auto-generated method stub
		return repo.findByUser_id(user_id);
	}

	@Override
	public List<Gamification> findTopTen() {
		// TODO Auto-generated method stub
		return repo.findTopTen();
	}

	@Override
	public int countByGamificationByUser(int user_id) {
		// TODO Auto-generated method stub
		return repo.countByGamificationByUser(user_id);
	}

	@Override
	public int findBySumGamePointsByUser(int user_id) {
		// TODO Auto-generated method stub
		return repo.findBySumGamePointsByUser(user_id);
	}

	@Override
	public List<Gamification> findAllUsersPositions() {
		// TODO Auto-generated method stub
		return repo.findAllUsersPositions();
	}

	@Override
	public void save(Gamification g) {
		// TODO Auto-generated method stub
		repo.save(g);
	}

		
	
	}
