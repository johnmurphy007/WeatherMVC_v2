package ie.cit.group3.repository;


import java.util.List;

import ie.cit.group3.entity.Users;




import org.springframework.data.jpa.repository.Query;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
/**
 * @author john murphy
 * 
 * This interface inherits the JPA/Hibernate CrudRepository to give access to UserRepository repository.
 *
 */
//@Qualifier(value = "userRepository")  //give the name we will assign to all autowired variables
public interface UserRepository extends CrudRepository<Users, Integer> {
	//CrudRepository gives us access to inherited methods like save, findAll, delete etc & Convention over coding.

	public Users findByUsername(String username);
	
	public List<Users> findAll();

	
}