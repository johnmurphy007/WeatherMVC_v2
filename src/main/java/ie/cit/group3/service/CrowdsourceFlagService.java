package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a CrowdsourcingFlag can use at the Service Layer.
 * Activities are:
		List<CrowdsourcingFlag> findByAdminreviewedIsFalse();
		void save(CrowdsourcingFlag descriptionFlagToUpdate);
 * 
 */

import ie.cit.group3.entity.CrowdsourcingFlag;

import java.util.List;

public interface CrowdsourceFlagService {
		
	public List<CrowdsourcingFlag> findByAdminreviewedIsFalse();

	public void save(CrowdsourcingFlag descriptionFlagToUpdate);

	}
