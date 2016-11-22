package ie.cit.group3.utility;

import ie.cit.group3.domain.Authorities;

import java.sql.ResultSet;
import java.sql.SQLException;






import org.springframework.jdbc.core.RowMapper;
/**
 * @author John Murphy
 * 
 * This class provides the mapping from a SQL resultset to an object. It returns the populated object when complete. 
 * The SQL table attributes are extracted using the rs.getString(<table attribute name here>)
 * 
 * In this case the object is an 'Authority'
 */
public class AuthoritiesRowMapper implements RowMapper<Authorities> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Authorities mapRow(ResultSet rs, int index) throws SQLException {
		
		Authorities authority = new Authorities();
		
		authority.setUsername(rs.getString("username"));
		authority.setAuthority(rs.getString("authority"));
	
		return authority;
	}
}
