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
 * This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 * 
 * This class is used when a user flags a comment as inappropriate. It captures the complaint.
 * 
 * This object is used as the form-backing bean for the modal complaint form as well.
 */
@Entity
@Table(name="comment_flag") //name of table in database
public class CommentFlag {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@OneToOne (fetch = FetchType.EAGER)//, mappedBy = "comment")
	@JoinColumn(name="gamification_id")  //name of fk column.
	private Gamification gamification;   //not used and can be deleted (will delete after project is submitted as weary of unintended consequences of making changes this late before submission.

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="flagusername") //This is the foreign key
	private Users user;		//User details of person who flagged the comment
	
	private String flagcomment;  //User entered text why they have flagged the comment
	private String flagsource;  //used to identify where the flag came from (e.g. crowdsourcing or comment etc)
	private int sourcecommentrowindex; //used to capture the row index of the comment when capturing the user complaint
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="flagchoice_id") //This is the foreign key
	private Flagchoice flagchoice;  //flagchoice is the list of options that a user can select as the reason why they are complaining/flagging the comment.

	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date flagdate;		//date the user flagged the comment.
	private Boolean adminreviewed = false;		//flag to indicate if an administrator has reviewed the complaint/flagged item.
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="comment_id")//, insertable=false, updatable=false)
	private Comment comment;	//link back to the comment that is flagged.  This is done using the 'sourcecommentrowindex' to work backwards to determine the id of the comment (this calculation is performed at the Controller layer).


	//Constructor
	public CommentFlag() {
		super();
	}


	@Override
	public String toString() {
		return "CommentFlag [id=" + id + ", gamification=" + gamification
				+ ", user=" + user + ", flagcomment=" + flagcomment
				+ ", flagsource=" + flagsource + ", sourcecommentrowindex="
				+ sourcecommentrowindex + ", flagdate=" + flagdate + ", adminreviewed="
				+ adminreviewed + ", comment=" + comment + "]";
	}

	//Getters & Setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Gamification getGamification() {
		return gamification;
	}


	public void setGamification(Gamification gamification) {
		this.gamification = gamification;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public String getFlagcomment() {
		return flagcomment;
	}


	public void setFlagcomment(String flagcomment) {
		this.flagcomment = flagcomment;
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


	public Flagchoice getFlagchoice() {
		return flagchoice;
	}


	public void setFlagchoice(Flagchoice flagchoice) {
		this.flagchoice = flagchoice;
	}


	public Date getFlagdate() {
		return flagdate;
	}


	public void setFlagdate(Date flagdate) {
		this.flagdate = flagdate;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public Boolean getAdminreviewed() {
		return adminreviewed;
	}


	public void setAdminreviewed(Boolean adminreviewed) {
		this.adminreviewed = adminreviewed;
	}




	
}
