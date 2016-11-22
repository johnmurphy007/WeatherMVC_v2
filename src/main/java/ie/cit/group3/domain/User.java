package ie.cit.group3.domain;

/**
 * @author John Murphy
 *	
 *	This class is used to capture the username, password and account enabled status. Object has 3 attributes: 
 *	username, password and enabled.
 * 
 * This class is used to  verify the username/password for a given user and if their account is active (i.e. enabled is true) 
 * or inactive (enabled is false).
 * 
 * Passwords are stored in encrypted format in the password field
 * 
 * I tried to extend the fields that this object could accept to include other user attributes (e.g. address, profilename etc)
 * but it would not accept these.  This is why I have a separate 'Users' object/entity.  All the usernames in this class (User) are
 * mirrored into the Users class.
 *  */

//@Entity  //indicates this is a JPA entity
//@Table(name="users") //name of table in database
public class User {
	
	private String username;  //mapped to column named 'username'
	private String password;	//password stored in encrypted format (encryption is performed by another class)
	private boolean enabled; //account status: active(true), disabled(false)
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}

	//Getters & Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}
