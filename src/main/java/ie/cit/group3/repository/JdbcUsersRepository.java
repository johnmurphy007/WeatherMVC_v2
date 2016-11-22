package ie.cit.group3.repository;

import ie.cit.group3.domain.User;
import ie.cit.group3.utility.UsersRowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author John Murphy
 * 
 * This class implements the interface for ParticipantRepository
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 *  List<User> findAllUsers(Boolean active) 
 */



//Identify this class as Repository (Spring will detect it during @ComponentScan).
@Repository
public class JdbcUsersRepository implements UsersRepository 
{
	
	//Using JdbcTemplate to provide repository access using SQL statements
	private JdbcTemplate jdbcTemplate;
	
	//Using Constructor DI, auto-inject the DataBase dependency connection into this object.
	@Autowired
	public JdbcUsersRepository(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User get(String username) 
	{
		/** This method retrieves the Users that a user has
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
		
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
		
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {username});
		
		if (count > 0)
		{
			sql = "SELECT * FROM users WHERE username = ?";

			User user = jdbcTemplate.queryForObject(sql, new Object [] {username}, new UsersRowMapper());
			
			return user;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void save(User user) 
	{

		String sql = "SELECT COUNT(username) FROM users WHERE username = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {user.getUsername()});
	
		if (count > 0)
			update(user);
		}
	
			

	
	private void update(User user)
	{
		//Method to update a record in the repository
		String sql = "UPDATE users SET enabled=? WHERE username = ?";
		jdbcTemplate.update(sql, new Object[] {user.isEnabled(), user.getUsername()});
	}
	
	@Override
	public void remove(User user) 
	{
		//Method to delete a record from the repository
		String sql = "DELETE FROM users WHERE username = ?";
		jdbcTemplate.update(sql, new Object[] {user.getUsername()});
	}

	@Override
	public List<User> findAll() 
	{
		//Return a List of all Participants
		String sql = "SELECT * FROM users";
		return jdbcTemplate.query(sql,  new UsersRowMapper());
	}

	public List<User> findAllUsers(Boolean active) 
	{
		//Return a List of all Participants
		String sql = "SELECT * FROM users WHERE enabled = ?";
		return jdbcTemplate.query(sql, new Object [] {active},  new UsersRowMapper());
	}
	
	
}
