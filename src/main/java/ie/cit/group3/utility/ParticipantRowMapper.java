package ie.cit.group3.utility;

import ie.cit.group3.domain.Participant;

import java.sql.ResultSet;
import java.sql.SQLException;




import org.springframework.jdbc.core.RowMapper;
/**
 * @author John Murphy
 * Date: 	31 March 2015
 * 
 * This class provides the mapping from a SQL resultset to an object. It returns the populated object when complete. 
 * The SQL table attributes are extracted using the rs.getString(<table attribute name here>)
 * 
 * In this case the object is a Participant
 */
public class ParticipantRowMapper implements RowMapper<Participant> {

	@Override
	public Participant mapRow(ResultSet rs, int index) throws SQLException {
		
		Participant participant = new Participant();
		
		participant.setPerson_date(rs.getString("person_date"));
		participant.setPerson_id(rs.getString("person_id"));
		participant.setPerson_name(rs.getString("person_name"));
		participant.setPerson_url(rs.getString("person_url"));
		
		return participant;
	}
}
