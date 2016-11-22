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
 *  This class is used to capture the gamification elements.
 *	
 */
@Entity
@Table(name="gamification") //name of table in database
public class Gamification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="user_id") //This is the foreign key
	private Users user;  //captures the user that gets credit for the gamification activity
	
	private String chobject_id;	//object that gamification was performed on (debatable whether this attribute is strictly needed here, but not changing it at present as too close to project submission)
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="game_id") //This is the foreign key
	private GameType gametype;  //game type played by user
	
	private int gamepoints;		//game points for the game played by the user. THis is captured at the time of game played as it is conceivable that a 
								//game could be promoted for a certain timeframe whereby it could be rewarded with additional points.
	@DateTimeFormat (pattern="dd-MM-YYYY")
	private Date datestamp;		//date when game was played

	@OneToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="id") //This is the foreign key	
	private Comment comment; 	//link to comments table

	@OneToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="id") //This is the foreign key	
	private CommentThumb commentThumb; //link to commentThumb table.
	

	
	public Gamification() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	public Gamification(Users user, int gamepoints) {
		super();
		this.user = user;
		this.gamepoints = gamepoints;
	}




	@Override
	public String toString() {
		return "Gamification [id=" + id + ", user=" + user + ", chobject_id="
				+ chobject_id + ", gametype=" + gametype + ", gamepoints="
				+ gamepoints + ", datestamp=" + datestamp + ", tagname="
				+", comment=" + comment + "]";
	}



	//Getters & Setters
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public CommentThumb getCommentThumb() {
		return commentThumb;
	}

	public void setCommentThumb(CommentThumb commentThumb) {
		this.commentThumb = commentThumb;
	}

	public GameType getGametype() {
		return gametype;
	}



	public void setGametype(GameType gametype) {
		this.gametype = gametype;
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



	public String getChobject_id() {
		return chobject_id;
	}



	public void setChobject_id(String chobject_id) {
		this.chobject_id = chobject_id;
	}



	public int getGamepoints() {
		return gamepoints;
	}



	public void setGamepoints(int gamepoints) {
		this.gamepoints = gamepoints;
	}



	public Date getDatestamp() {
		return datestamp;
	}



	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}

}
