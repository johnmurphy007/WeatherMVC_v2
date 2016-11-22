package ie.cit.group3.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author john murphy
 *	
 *This class is used as the form backing bean to gather and display user related data.  
 *Actual passwords and account status are not recorded by this class - the 'User' class is used for these (encrypted password and enabled attributes).
 *
 * I tried to combine the attributes in this class with the attributes from 'User' class, but it would not work for me.  This is why there areseparate 
 *	Users' object/entity.  
 */

@Entity  //indicates this is a JPA entity
@Table(name="user") //name of table in database
public class Users {
	
	@Id
	//@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures the ID is generated automatically and is compatible with underlying database AI implementation
	private int id;
	
	@OneToMany (mappedBy = "user")//(cascade={CascadeType.ALL})
	private List<Gamification> gamification;
	
	@OneToMany (mappedBy = "user")//(cascade={CascadeType.ALL})
	private List<Crowdsourcing> crowdsourcing;
	
	private String username;  //mapped to column named 'username'
	private String password;	//used only to capture the user input for password. Password is not saved in this field.
	private String authority; //By default 'USER'.
	private String address1;	
	private String address2;
	private String address3;
	private String email;
	private int age;
	private boolean accountstatus;
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date datejoined;

	@OneToMany (mappedBy = "user")
	//@OneToOne (fetch = FetchType.EAGER, mappedBy = "gametype")
	private List<Comment> comment;
	//public Object encoder;
	
	@OneToMany (mappedBy = "user")
	//@OneToOne (fetch = FetchType.EAGER, mappedBy = "gametype")
	private List<CommentThumb> commentThumb;
	
	@OneToMany (mappedBy = "user")
	private List<CommentFlag> commentFlag;
	

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public Users(String username) {
	super();
	this.username = username;
}
	
	
	
//	public Users(String username,
//			String password, String profilename, String address1,
//			String address2, String address3, String email, int age,
//			boolean accountstatus, Date datejoined, Gamification gamification) {
//		super();
//		this.gamification.add(gamification);
//		this.username = username;
//		this.password = password;
//		this.authority = profilename;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.address3 = address3;
//		this.email = email;
//		this.age = age;
//		this.accountstatus = accountstatus;
//		this.datejoined = datejoined;
//	}

//	public Users(String username,String password, String profilename, String address1,
//			String address2, String address3, String email, int age,
//			boolean accountstatus, Date datejoined,List<Gamification> gamification) {
//		super();
//		this.gamification = gamification;
//
//		this.username = username;
//		this.password = password;
//		this.authority = profilename;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.address3 = address3;
//		this.email = email;
//		this.age = age;
//		this.accountstatus = accountstatus;
//		this.datejoined = datejoined;
//	}



//	public Users(Crowdsourcing crowdsourcing, String username,
//			String password, String profilename, String address1,
//			String address2, String address3, String email, int age,
//			boolean accountstatus, Date datejoined) {
//		super();
//		this.crowdsourcing.add(crowdsourcing);
//		this.username = username;
//		this.password = password;
//		this.authority = profilename;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.address3 = address3;
//		this.email = email;
//		this.age = age;
//		this.accountstatus = accountstatus;
//		this.datejoined = datejoined;
//	}


//	public Users(List<Crowdsourcing> crowdsourcing, String username,
//			String password, String profilename, String address1,
//			String address2, String address3, String email, int age,
//			boolean accountstatus, Date datejoined) {
//		super();
//		this.crowdsourcing = crowdsourcing;
//		this.username = username;
//		this.password = password;
//		this.authority = profilename;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.address3 = address3;
//		this.email = email;
//		this.age = age;
//		this.accountstatus = accountstatus;
//		this.datejoined = datejoined;
//	}

//	public Users(String username, String password, String profilename,
//			String address1, String address2, String address3, String email,
//			int age, boolean accountstatus,  Date datejoined) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.authority = profilename;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.address3 = address3;
//		this.email = email;
//		this.age = age;
//		this.accountstatus = accountstatus;
//		this.datejoined = datejoined;
//	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", profilename=" + authority + ", address1="
				+ address1 + ", address2=" + address2 + ", address3="
				+ address3 + ", email=" + email + ", age=" + age
				+ ", accountstatus=" + accountstatus + ", datejoined=" + datejoined 
				+ "]";
	}


//Getters & Setters
	
	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	
	public List<Gamification> getGamification() {
		return gamification;
	}

	public void setGamification(List<Gamification> gamification) {
		this.gamification = gamification;
	}

	public List<Crowdsourcing> getCrowdsourcing() {
		return crowdsourcing;
	}

	public void setCrowdsourcing(List<Crowdsourcing> crowdsourcing) {
		this.crowdsourcing = crowdsourcing;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



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



	public String getProfilename() {
		return authority;
	}



	public void setProfilename(String profilename) {
		this.authority = profilename;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getAddress3() {
		return address3;
	}



	public void setAddress3(String address3) {
		this.address3 = address3;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public boolean isAccountstatus() {
		return accountstatus;
	}



	public void setAccountstatus(boolean accountstatus) {
		this.accountstatus = accountstatus;
	}


	public Date getDatejoined() {
		return datejoined;
	}



	public void setDatejoined(Date datejoined) {
		this.datejoined = datejoined;
	}

	public List<CommentThumb> getCommentThumb() {
		return commentThumb;
	}

	public void setCommentThumb(List<CommentThumb> commentThumb) {
		this.commentThumb = commentThumb;
	}

	public List<CommentFlag> getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(List<CommentFlag> commentFlag) {
		this.commentFlag = commentFlag;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	
}
