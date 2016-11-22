package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a CrowdsourceFlagService can use at the Service Layer.
 * Activities are:
 * 		List<CrowdsourcingFlag> findByAdminreviewedIsFalse()
		void save(CrowdsourcingFlag descriptionFlagToUpdate)
 */



import ie.cit.group3.entity.CrowdsourcingFlag;
import ie.cit.group3.repository.CrowdsourceFlagRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrowdsourceFlagServiceImpl implements CrowdsourceFlagService{
		
	@Autowired
	CrowdsourceFlagRepository repo;
	
	public CrowdsourceFlagServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Autowire this object, using constructor DI.
	@Autowired
	public CrowdsourceFlagServiceImpl (CrowdsourceFlagRepository repo)
	{
		this.repo = repo;
	}

	@Override
	public List<CrowdsourcingFlag> findByAdminreviewedIsFalse() {
		// TODO Auto-generated method stub
		return repo.findByAdminreviewedIsFalse();
	}

	@Override
	public void save(CrowdsourcingFlag descriptionFlagToUpdate) {
		// TODO Auto-generated method stub
		repo.save(descriptionFlagToUpdate);
	}
	

	
	}
