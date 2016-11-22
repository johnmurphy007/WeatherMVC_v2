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
*  This class is used to capture the Crowdsourcing descriptions that are flagged as inappropriate.
*/

@Entity
@Table(name="crowdsourcing_flag") //name of table in database
public class CrowdsourcingFlag {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="flagusername") //This is the foreign key
	private Users user;  //user that flagged the description
		
	private String flagcomment;  //text user that flagged the description can provide.
	private String flagsource;  //used to identify where the flag came from (e.g. crowdsourcing or comment etc)
	private int sourcecommentrowindex; //used to capture the row index of the comment when capturing the user complaint
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="flagchoice_id") //This is the foreign key
	private Flagchoice flagchoice;	//option that user selected for flagging the description

	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date flagdate;  //date description was flagged 
	private Boolean adminreviewed = false;  //flag used to determine if administrator has reviewed the flagged description.

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="crowdsourcing_id")//, insertable=false, updatable=false)
	private Crowdsourcing crowdsourcing;  //link back to original description that has been flagged (calculation on which description is performed at controller layer)


	//Constructor.  Empty constructor used by JPA
	public CrowdsourcingFlag() {
		super();
	}



	@Override
	public String toString() {
		return "CrowdsourcingFlag [id=" + id + ", user=" + user
				+ ", flagcomment=" + flagcomment + ", flagsource=" + flagsource
				+ ", sourcecommentrowindex=" + sourcecommentrowindex
				+ ", flagchoice=" + flagchoice + ", flagdate=" + flagdate
				+ ", adminreviewed=" + adminreviewed
				+ "]";
	}


	//getters & setters
	public Crowdsourcing getCrowdsourcing() {
		return crowdsourcing;
	}



	public void setCrowdsourcing(Crowdsourcing crowdsourcing) {
		this.crowdsourcing = crowdsourcing;
	}



	public String getFlagcomment() {
		return flagcomment;
	}

	public void setFlagcomment(String flagcomment) {
		this.flagcomment = flagcomment;
	}

	public Flagchoice getFlagchoice() {
		return flagchoice;
	}

	public void setFlagchoice(Flagchoice flagchoice) {
		this.flagchoice = flagchoice;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}

	public Date getFlagdate() {
		return flagdate;
	}


	public void setFlagdate(Date flagdate) {
		this.flagdate = flagdate;
	}


	public Boolean getAdminreviewed() {
		return adminreviewed;
	}



	public void setAdminreviewed(Boolean adminreviewed) {
		this.adminreviewed = adminreviewed;
	}



	public String getFlagsource() {
		return flagsource;
	}

	public void setFlagsource(String flagsource) {
		this.flagsource = flagsource;
	}

	public int getSourcecommentrowindex() {
		return sourcecommentrowindex;
	}

	public void setSourcecommentrowindex(int sourcecommentrowindex) {
		this.sourcecommentrowindex = sourcecommentrowindex;
	}
	

	
	
}
