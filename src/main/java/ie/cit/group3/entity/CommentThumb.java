package ie.cit.group3.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author john murphy
 * 
 *  This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 *	
 *	This class is used when a user clicks to 'Thumbup' or 'Thumbdown' an object.
 *
 */
@Entity
@Table(name="comment_thumb") //name of table in database
public class CommentThumb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id") //This is the foreign key
	private Users user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="comment_id")//, insertable=false, updatable=false)
	private Comment comment;
	
	private boolean thumbup;

	private boolean thumbdown;
	
	@OneToOne (fetch = FetchType.EAGER)//, mappedBy = "comment")
	@JoinColumn(name="gamification_id")  //name of fk column.
	private Gamification gamification;
	
	
//toString	

@Override
	public String toString() {
		return "CommentThumb [id=" + id +  ", thumbup="
				+ thumbup + ", thumbdown=" + thumbdown +  "]";
	}

//constructors

	public CommentThumb() {  //used by JPA
		super();
		// TODO Auto-generated constructor stub
	}
	
/*	public CommentThumb(int id, Comment comment, boolean thumbup,
		boolean thumbdown, Gamification gamification) {
	super();
	this.id = id;
	this.comment = comment;
	this.thumbup = thumbup;
	this.thumbdown = thumbdown;
//	this.gamification = gamification;
}
*/


	public CommentThumb(Comment comment, boolean thumbup, boolean thumbdown) {
		super();
		this.comment = comment;
		this.thumbup = thumbup;
		this.thumbdown = thumbdown;
	}

	//getters & setters
	
	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public Gamification getGamification() {
		return gamification;
	}



	public void setGamification(Gamification gamification) {
		this.gamification = gamification;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}



	public boolean isThumbup() {
		return thumbup;
	}



	public void setThumbup(boolean thumbup) {
		this.thumbup = thumbup;
	}



	public boolean isThumbdown() {
		return thumbdown;
	}



	public void setThumbdown(boolean thumbdown) {
		this.thumbdown = thumbdown;
	}



}
