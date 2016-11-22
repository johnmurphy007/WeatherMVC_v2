package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a TagName can use at the Service Layer.
 * Activities are:
 *  save		Save a object/record to the repository
 * 	List<TagName> findBychobject_id(String id);	
	Page<TagName> findByTagwordLike(String tagword, Pageable pageable);
	long countByTagwordLike(String tagword);

 */



import java.util.List;

import ie.cit.group3.entity.TagName;
import ie.cit.group3.repository.TagNameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagNameServiceImpl implements TagNameService{
		
	@Autowired
	TagNameRepository repo;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public TagNameServiceImpl (TagNameRepository repo)
	{
		this.repo = repo;
	}

	public TagNameServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TagName> findBychobject_id(String id) {
		// TODO Auto-generated method stub
		return repo.findBychobject_id(id);
	}

	@Override
	public Page<TagName> findByTagwordLike(String tagword, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByTagwordLike(tagword, pageable);
	}

	@Override
	public long countByTagwordLike(String tagword) {
		// TODO Auto-generated method stub
		return repo.countByTagwordLike(tagword);
	}

	@Override
	public void save(TagName tagname) {
		// TODO Auto-generated method stub
		repo.save(tagname);
	}

	
	
	}
