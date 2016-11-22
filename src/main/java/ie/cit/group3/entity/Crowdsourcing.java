package ie.cit.group3.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author john murphy
 *	
 *  This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 *  
 *  This class is used to capture the Crowdsourcing descriptions that 'Trusted' users can provide for ChObjects.
 *  
 */
@Entity
@Table(name="crowdsourcing") //name of table in database
public class Crowdsourcing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="user_id") //This is the foreign key
	private Users user;		//user that submitted the description

	String chobject_id;	//object id that new description is added too.
	
	
	private String description;	//user description
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date datestamp;		//date user added description
	private boolean flag = false;		//flag to identify that a description has been flagged as inappropriate
	
	@OneToMany (mappedBy = "crowdsourcing")
	private List<CrowdsourcingFlag> crowdsourcingFlag;
	
	
	//Constructor
	public Crowdsourcing() {
		super();
		// TODO used by JPA
	}


	@Override
	public String toString() {
		return "Crowdsourcing [id=" + id + ", user=" + user + ", chobject_id="
				+ chobject_id + ", description=" + description + ", datestamp="
				+ datestamp + ", flag=" + flag + ", crowdsourcingFlag="
				+ crowdsourcingFlag + "]";
	}




//Getters & Setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public Users getUsers() {
		return user;
	}

	public void setUsers(Users user) {
		this.user = user;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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



	public Date getDatestamp() {
		return datestamp;
	}
	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<CrowdsourcingFlag> getCrowdsourcingFlag() {
		return crowdsourcingFlag;
	}



	public void setCrowdsourcingFlag(List<CrowdsourcingFlag> crowdsourcingFlag) {
		this.crowdsourcingFlag = crowdsourcingFlag;
	}
	
	

}
