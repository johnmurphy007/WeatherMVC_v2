package ie.cit.group3.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author john murphy
 *	
 *  This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 *  
 *  This class is used to capture the choice the user makes when flagging a comment or descriptions
 *  
 */
@Entity
@Table(name="flagchoice") //name of table in database

public class Flagchoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
	
	@OneToMany (mappedBy = "flagchoice")//(cascade={CascadeType.ALL})
	private List<CommentFlag> commentflag;
	
	private String commentchoices;  //Reason why a comment/description is flagged

	public Flagchoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Flagchoice [id=" + id + ", commentchoices=" + commentchoices + "]";
	}

	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CommentFlag> getCommentflag() {
		return commentflag;
	}

	public void setCommentflag(List<CommentFlag> commentflag) {
		this.commentflag = commentflag;
	}

	public String getCommentchoices() {
		return commentchoices;
	}

	public void setCommentchoices(String commentchoices) {
		this.commentchoices = commentchoices;
	}
	
	
}
