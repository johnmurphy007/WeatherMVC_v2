package ie.cit.group3.repository;


import ie.cit.group3.domain.ChObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate PagingAndSortingRepository to give access to JPAChObjectRepository.
 *
 */
public interface JPAChObjectRepository extends PagingAndSortingRepository<ChObject,Integer> {
	//PagingAndSortingRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding & Paging/Sorting methods.
	
//	Using Convention over coding to generate the following queries on the ChObject Repository

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