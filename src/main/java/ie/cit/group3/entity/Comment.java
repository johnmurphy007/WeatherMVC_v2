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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author john Murphy
 * 
 * This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 * 
 * This class captures a comment that a user may enter for a Cultural Heritage Object.
 *	
 */
@Entity
@Table(name="comment") //name of table in database
public class Comment {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@OneToOne (fetch = FetchType.EAGER)//, mappedBy = "comment")  //each row in this table/object is mapped to one row in gamification table.
	@JoinColumn(name="gamification_id")  //name of fk column.
	private Gamification gamification;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id") //This is the foreign key
	private Users user;			//JPA will update the comment table with the user_id to provide the link between comment table and user
	
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date datestamp;
	private String commenttext;   	//This is the text that a user will enter as a comment.
	private boolean flag = false;	//used to identify if the comment has been flagged as inappropriate.
	private int thumbupcount = 0;	//used to display the count of thumbups this comment has received
	private int thumbdowncount = 0;	//used to display the count of thumbdowns this comment has received
	
	@OneToMany (mappedBy = "comment")
	private List<CommentThumb> commentThumb;
	
//	@ManyToOne (fetch = FetchType.EAGER)//, cascade={CascadeType.ALL})
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinColumn(name="chobject_id") //This is the foreign key to join this object to chObject table.
//	@JoinColumn(name="chobject_id", insertable=false, updatable=false)
//	private ChObject chobject;
	private String chobject_id;  		//This is the link to the Chobject (via chobject_id)
	
	
	
	//Constructors:
	public Comment(String commenttext) {
		super();
		this.commenttext = commenttext;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "Comment [id=" + id + ", gamification=" + gamification
				+ ", user=" + user + ", datestamp=" + datestamp
				+ ", commenttext=" + commenttext + ", flag=" + flag
				+ ", thumbupcount=" + thumbupcount + ", thumbdowncount="
				+ thumbdowncount + ", commentThumb=" + commentThumb
				+ ", chobject_id=" + chobject_id + "]";
	}

	
	//Getters & Setters
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommenttext() {
		return commenttext;
	}
	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}

	public Gamification getGamification() {
		return gamification;
	}

	public void setGamification(Gamification gamification) {
		this.gamification = gamification;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getChobject_id() {
		return chobject_id;
	}

	public void setChobject_id(String chobject_id) {
		this.chobject_id = chobject_id;
	}

	public int getThumbupcount() {
		return thumbupcount;
	}

	public void setThumbupcount(int thumbupcount) {
		this.thumbupcount = thumbupcount;
	}

	public int getThumbdowncount() {
		return thumbdowncount;
	}

	public void setThumbdowncount(int thumbdowncount) {
		this.thumbdowncount = thumbdowncount;
	}

	public List<CommentThumb> getCommentThumb() {
		return commentThumb;
	}

	public void setCommentThumb(List<CommentThumb> commentThumb) {
		this.commentThumb = commentThumb;
	}

}
