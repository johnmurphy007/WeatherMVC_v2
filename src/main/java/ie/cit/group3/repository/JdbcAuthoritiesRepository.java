package ie.cit.group3.repository;

import ie.cit.group3.domain.Authorities;
//import ie.cit.group3.utility.ParticipantRowMapper;

import ie.cit.group3.utility.AuthoritiesRowMapper;
import ie.cit.group3.utility.DistinctAuthorityRowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author John Murphy
 * 
 * This class implements the interface for AuthoritiesRepository
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 
 */



//Identify this class as Repository (Spring will detect it during @ComponentScan).
@Repository
public class JdbcAuthoritiesRepository implements AuthoritiesRepository 
{
	
	//Using JdbcTemplate to provide repository access using SQL statements
	private JdbcTemplate jdbcTemplate;
	
	//Using Constructor DI, auto-inject the DataBase dependency connection into this object.
	@Autowired
	public JdbcAuthoritiesRepository(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Authorities get(String username) 
	{
		/** @Param username
		 * This method retrieves the Authority that a user has based on their username.
		 * 
		 * Need to check that the record exists in the database first before returning it.
		 * Otherwise if the record does not exist, you will get an exception that the application
		 * cannot handle.
		 * Using a SQL COUNT statement to return the number of records.  This will return an integer
		 * 'count'.  If this is 0, then no match exists and the return type of the object is set to 
		 * null. If 'count' is >0, then run the SQL query to return the number of records that match
		 * the supplied String 'username'.
		 * 
		 * */
		
		String sql = "SELECT COUNT(*) FROM authorities WHERE username = ?";
		
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {username});
		
		if (count > 0)
		{
			sql = "SELECT * FROM authorities WHERE username = ?";

			Authorities authority = jdbcTemplate.queryForObject(sql, new Object [] {username}, new AuthoritiesRowMapper());
			
			return authority;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void save(Authorities authority) 
	{
		/** @Param authority object
		 * This method saves an Authorities object to the repository
		 * 
		 * A check is performed to see if the record exists in the repo, or not.  If it already exists, 
		 * the update() method is called, else the add() method is called. 
		 * 
		 * Using a SQL COUNT statement to return the number of records.  This will return an integer
		 * 'count'.  If this is 0, then no record exists and add() is called.
		 * 
		 * */
		String sql = "SELECT COUNT(username) FROM authorities WHERE username = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {authority.getUsername()});
	
		if (count > 0)
			update(authority);
		else
			add(authority);
		}
	
	private void add(Authorities authority)
	{
		/** @Param authority object
		 * Private method: This method saves an Authorities object to the repository
		 * 
		 * */
		String sql = "INSERT INTO authorities (username, authority) VALUES (?,?);";
			jdbcTemplate.update(sql, new Object[] {authority.getUsername(), authority.getAuthority()});	
	}
				

	
	private void update(Authorities authority)
	{
		/** @Param authority object
		 * Private method: This method updates an Authorities object in the repository
		 * 
		 * */
		String sql = "UPDATE authorities SET authority=? WHERE username = ?";
		jdbcTemplate.update(sql, new Object[] {authority.getAuthority(),authority.getUsername()});
	}
	
	@Override
	public void remove(Authorities authority) 
	{
		/** @Param authority object
		 * Method to remove an Authorities object from the repository
		 * 
		 * */
		String sql = "DELETE FROM authorities WHERE username = ?";
		jdbcTemplate.update(sql, new Object[] {authority.getUsername()});
	}

	@Override
	public List<Authorities> findAll() 
	{
		/** @Param none
		 * @return List of Authorities objects
		 * Method returns a list of all Authorities in the repository
		 */
		String sql = "SELECT * FROM authorities";
		return jdbcTemplate.query(sql,  new AuthoritiesRowMapper());
	}

	public List<Authorities> findAllUsers(String usertype) 
	{
		/** @Param usertype = USER, ADMIN, TRUSTED
		 * @return List of Authorities objects that meet search criteria
		 * Method returns a list of all Authorities in the repository that meet search criteria
		 */
		String sql = "SELECT * FROM authorities WHERE authority = ?";
		return jdbcTemplate.query(sql, new Object [] {usertype},  new AuthoritiesRowMapper());
	}
	
	@Override
	public List<Authorities> findDistinctAuthorities() 
	{
		/** 
		 * @return List of Authorities objects that meet query criteria
		 * Method returns a list of all the distinct Authorities in the repository
		 * */
		String sql = "SELECT DISTINCT authority FROM authorities";
		return jdbcTemplate.query(sql, new DistinctAuthorityRowMapper());
		
	}

}
