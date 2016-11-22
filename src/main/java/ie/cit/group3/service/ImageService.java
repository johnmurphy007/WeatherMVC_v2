package ie.cit.group3.service;


import ie.cit.group3.domain.Image;

import java.util.List;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that an Image can use at the Service Layer.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 *  List<Image> find(String searchcriteria);
    List<Image> findByImageId(String searchcriteria);
	long countByImageId(String searchcriteria);
 */

public interface ImageService {
	
	public Image get (String id, String resolution);
	
	public void save(Image image);
	
	public void remove(Image image);
	
	public List<Image> findAll();

	public List<Image> find(String searchcriteria);

	public List<Image> findByImageId(String searchcriteria);

	public long countByImageId(String searchcriteria);


}
