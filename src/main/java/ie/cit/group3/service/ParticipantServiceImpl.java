package ie.cit.group3.service;


import ie.cit.group3.domain.Participant;
import ie.cit.group3.repository.ParticipantRepository;
import ie.cit.group3.utility.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author John Murphy
 * 
 * This class implements the interface for ParticipantService.  
 * 
 * This class passes directly through to the Repository layer.
 * 
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 	int CountAll();
	List<Participant> findByPersonId(String searchpeople);
	Page<Participant> findAll(int pageNo, int pageSize);
	long countByParticipant(String id);
 */


//Identify this class as Service (Spring will detect it during @ComponentScan & create a bean of this type).
@Service
public class ParticipantServiceImpl implements ParticipantService {

	//instance variable that is updated via constructor DI
	ParticipantRepository participantRepository;
	
	//Autowire this object, using constructor DI.	
	@Autowired
	public ParticipantServiceImpl (ParticipantRepository participantRepository)
	{
		this.participantRepository = participantRepository;
	}
	
	@Override
	public Participant get(String id) 
	{
		return participantRepository.get(id);
	}

	@Override
	public void save(Participant participant) 
	{
		participantRepository.save(participant);
	}

	@Override
	public void remove(Participant participant) 
	{
		participantRepository.remove(participant);
	}

	@Override
	public List<Participant> findAll() 
	{
		return participantRepository.findAll();
	}

	@Override
	public int CountAll() {
		// TODO Auto-generated method stub
		return participantRepository.CountAll();
	}

	@Override
	public List<Participant> findByPersonId(String searchpeople) {
		// TODO Auto-generated method stub
		return participantRepository.findByPersonId(searchpeople);
	}

	@Override
	public Page<Participant> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return participantRepository.findAll(pageNo, pageSize);
	}

	@Override
	public long countByParticipant(String id) {
		// TODO Auto-generated method stub
		return participantRepository.countByParticipant(id);
	}

}
