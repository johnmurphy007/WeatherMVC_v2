package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a ChObject can use at the Service Layer.
 * Using convention over coding for queries on repository
 * 
 * Activities are:
 * 	Page<ChObject> findByTitleLikeOrderByTitleAsc(String title, Pageable pageable);
	public Page<ChObject> findByTitleLikeOrderByTitleDesc(String title, Pageable pageable);
	public Page<ChObject> findByTitleLike(String title, Pageable pageable);
	long countByTitleLike(String title);

	public Page<ChObject> findByDescriptionLikeOrderByDescriptionAsc(String description,Pageable pageable);
	public Page<ChObject> findByDescriptionLikeOrderByDescriptionDesc(String description,Pageable pageable);
	public Page<ChObject> findByDescriptionLike(String description,Pageable pageable);
	long countByDescriptionLike(String description);
	
	public Page<ChObject> findByMediumLikeOrderByMediumAsc(String medium, Pageable pageable);
	public Page<ChObject> findByMediumLikeOrderByMediumDesc(String medium, Pageable pageable);
	public Page<ChObject> findByMediumLike(String medium, Pageable pageable);
	long countByMediumLike(String medium);
	
	public Page<ChObject> findByCreditlineLikeOrderByCreditlineAsc(String creditline, Pageable pageable);
	public Page<ChObject> findByCreditlineLikeOrderByCreditlineDesc(String creditline, Pageable pageable);
	public Page<ChObject> findByCreditlineLike(String creditline, Pageable pageable);
	long countByCreditlineLike(String creditline);
 */



import ie.cit.group3.domain.ChObject;
import ie.cit.group3.repository.JPAChObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JPAChObjectServiceImpl implements JPAChObjectService{
		
	@Autowired
	JPAChObjectRepository repo;
	
	//Autowire this object, using constructor DI.
	@Autowired
	public JPAChObjectServiceImpl (JPAChObjectRepository repo)
	{
		this.repo = repo;
	}

	public JPAChObjectServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<ChObject> findByTitleLikeOrderByTitleAsc(String title,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByTitleLikeOrderByTitleAsc(title, pageable);
	}

	@Override
	public Page<ChObject> findByTitleLikeOrderByTitleDesc(String title,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByTitleLikeOrderByTitleDesc(title, pageable);
	}

	@Override
	public Page<ChObject> findByTitleLike(String title, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByTitleLike(title, pageable);
	}

	@Override
	public long countByTitleLike(String title) {
		// TODO Auto-generated method stub
		return repo.countByTitleLike(title);
	}

	@Override
	public Page<ChObject> findByDescriptionLikeOrderByDescriptionAsc(
			String description, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByDescriptionLikeOrderByDescriptionAsc(description, pageable);
	}

	@Override
	public Page<ChObject> findByDescriptionLikeOrderByDescriptionDesc(
			String description, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByDescriptionLikeOrderByDescriptionDesc(description, pageable);
	}

	@Override
	public Page<ChObject> findByDescriptionLike(String description,
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
	public Page<ChObject> findByMediumLikeOrderByMediumAsc(String medium,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByMediumLikeOrderByMediumAsc(medium, pageable);
	}

	@Override
	public Page<ChObject> findByMediumLikeOrderByMediumDesc(String medium,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByMediumLikeOrderByMediumDesc(medium, pageable);
	}

	@Override
	public Page<ChObject> findByMediumLike(String medium, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByMediumLike(medium, pageable);
	}

	@Override
	public long countByMediumLike(String medium) {
		// TODO Auto-generated method stub
		return repo.countByMediumLike(medium);
	}

	@Override
	public Page<ChObject> findByCreditlineLikeOrderByCreditlineAsc(
			String creditline, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByCreditlineLikeOrderByCreditlineAsc(creditline, pageable);
	}

	@Override
	public Page<ChObject> findByCreditlineLikeOrderByCreditlineDesc(
			String creditline, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByCreditlineLikeOrderByCreditlineDesc(creditline, pageable);
	}

	@Override
	public Page<ChObject> findByCreditlineLike(String creditline,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByCreditlineLike(creditline, pageable);
	}

	@Override
	public long countByCreditlineLike(String creditline) {
		// TODO Auto-generated method stub
		return repo.countByCreditlineLike(creditline);
	}

		
	
	}
