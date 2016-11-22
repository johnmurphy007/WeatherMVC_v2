package ie.cit.group3.utility;

import ie.cit.group3.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;






import org.springframework.jdbc.core.RowMapper;
/**
 * @author John Murphy
 * 
 * This class provides the mapping from a SQL resultset to an object. It returns the populated object when complete. 
 * The SQL table attributes are extracted using the rs.getString(<table attribute name here>)
 * 
 * In this case the object is User
 */
public class UsersRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int index) throws SQLException {
		
		User user = new User();
		
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getBoolean("enabled"));
	
		return user;
	}
}
