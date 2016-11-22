package ie.cit.group3.utility;

import ie.cit.group3.domain.Authorities;

import java.sql.ResultSet;
import java.sql.SQLException;






import java.util.List;

import org.springframework.jdbc.core.RowMapper;
/**
 * @author John Murphy
 * 
 * This class provides the mapping from a SQL resultset to an object. It returns the populated object when complete. 
 * The SQL table attributes are extracted using the rs.getString(<table attribute name here>)
 * 
 * In this case the object is a Authority object.
 * Used by the method: public List<Authorities> findDistinctAuthorities()  from AuthoritiesRepository class.
 */
public class DistinctAuthorityRowMapper implements RowMapper<Authorities> {

	@Override
	public Authorities mapRow(ResultSet rs, int index) throws SQLException {
		
		Authorities authority = new Authorities();
		
	//	authority.setUsername(rs.getString("username"));
		authority.setAuthority(rs.getString("authority"));
	
		return authority;
	}
}
