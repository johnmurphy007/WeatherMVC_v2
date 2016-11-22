package ie.cit.group3.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author john murphy
 *	
 *  This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 *  
 *  This class is used to capture when a user enters a Tag word for a ChObject. 
 *	
 */
@Entity
@Table(name="tagname") //name of table in database
public class TagName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	private String tagword;  //user inputed tagword
//JM 16-5-15
	@OneToOne (fetch = FetchType.EAGER)//, mappedBy = "comment") //populate this object immediately when query runs.
	@JoinColumn(name="gamification_id")  //name of fk column.
	private Gamification gamification;
	
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date datestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id") //This is the foreign key
	private Users user;  //link to user that inputted the tagword(s)

	private String chobject_id;
//END OF INSTANCE VARIABLES
	

@Override
	public String toString() {
		return "TagName [id=" + id + ", tagword=" + tagword + ", gamification="
				+ gamification + ", datestamp=" + datestamp + ", user=" + user
				+ ", chobject_id=" + chobject_id + "]";
	}


	public TagName() {  //used by JPA
		super();
		// TODO Auto-generated constructor stub
	}


	//Getters & Setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTagword() {
		return tagword;
	}


	public void setTagword(String tagword) {
		this.tagword = tagword;
	}


	public Gamification getGamification() {
		return gamification;
	}


	public void setGamification(Gamification gamification) {
		this.gamification = gamification;
	}


	public Date getDatestamp() {
		return datestamp;
	}


	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public String getChobject_id() {
		return chobject_id;
	}


	public void setChobject_id(String chobject_id) {
		this.chobject_id = chobject_id;
	}

//getters & setters

	

}
