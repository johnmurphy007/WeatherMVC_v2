package ie.cit.group3.domain;


/**
 * @author John Murphy
 * 
 *	This class is used in conjunction with 'User' class to provide the authority/access
 * 	level for a given username.  This object has 2 attributes: username and authority.
 * 	
 */

//@Entity  //indicates this is a JPA entity
//@Table(name="authorities") //name of table in database
public class Authorities {
	
	private String username;  //mapped to column named 'username'. Used to capture username
	private String authority; //used to capture authority of user (e.g. USER, TRUSTED, ADMIN)
	
	public Authorities() {
		super();
		// TODO Empty constructor
	}

	@Override
	public String toString() {
		return "Authorities [username=" + username + ", authority=" + authority
				+ "]";
	}

	//Getters & setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


}
