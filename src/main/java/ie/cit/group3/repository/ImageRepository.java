package ie.cit.group3.repository;

import ie.cit.group3.domain.ChObject;
import ie.cit.group3.domain.Image;
import ie.cit.group3.utility.Page;

import java.util.List;

/**
 * @author John Murphy
 * 
 * This interface defines the contract/methods that an Image can use on a Repository.
 * Activities are:
 * 	get			Retrieve record(s) that match a given id & resolution
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 	List<Image> find(String id);  //JM added 5/5/2015
 *  List<Image> findByImageId(String id)
 *  long countByImageId(String id)
 */

public interface ImageRepository {
	
	public Image get (String id, String resolution);
	
	public void save(Image image);
	
	public void remove(Image image);
	
	public List<Image> findAll();

	public List<Image> find(String id);  //JM added 5/5/2015

	public List<Image> findByImageId(String id);

	public long countByImageId(String id);

}
