package ie.cit.group3.service;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that a ChObject Entity object can use at the Service Layer.
 * 
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
 * 
 */




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ie.cit.group3.domain.ChObject;




public interface JPAChObjectService {
		
	public Page<ChObject> findByTitleLikeOrderByTitleAsc(String title, Pageable pageable);
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
	
	}
