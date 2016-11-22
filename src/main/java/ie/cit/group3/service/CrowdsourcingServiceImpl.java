package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a CrowdsourcingService can use at the Service Layer.
 * Activities are:
 * 	List<Crowdsourcing> findBychobject_id(String id) 
 *  List<Crowdsourcing> findBychobject_idANDflagFalse(String id)
	Page<Crowdsourcing> findByDescriptionLike(String description, Pageable pageable) 
	long countByDescriptionLike(String description)
	Crowdsourcing findOne(int id)
	void save(Crowdsourcing crowdsourcing) 
 */



import ie.cit.group3.entity.Crowdsourcing;
import ie.cit.group3.repository.CrowdsourcingRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CrowdsourcingServiceImpl implements CrowdsourcingService{
		
	@Autowired
	CrowdsourcingRepository repo;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public CrowdsourcingServiceImpl (CrowdsourcingRepository repo)
	{
		this.repo = repo;
	}



	public CrowdsourcingServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public List<Crowdsourcing> findBychobject_id(String id) {
		// TODO Auto-generated method stub
		return repo.findBychobject_id(id);
	}

	@Override
	public List<Crowdsourcing> findBychobject_idANDflagFalse(String id) {
		// TODO Auto-generated method stub
		return repo.findBychobject_idANDflagFalse(id);
	}

	@Override
	public Page<Crowdsourcing> findByDescriptionLike(String description,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByDescriptionLike(description, pageable);
	}

	@Override
	public long countByDescriptionLike(String description) {
		// TODO Auto-generated method stub
		return repo.countByDescriptionLike(description);
	}



	@Override
	public Crowdsourcing findOne(int id) {
		// TODO Auto-generated method stub
		return repo.findOne(id);
	}



	@Override
	public void save(Crowdsourcing crowdsourcing) {
		// TODO Auto-generated method stub
		repo.save(crowdsourcing);
	}
	

	
	}
