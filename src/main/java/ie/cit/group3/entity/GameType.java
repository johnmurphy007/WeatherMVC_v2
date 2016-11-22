package ie.cit.group3.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author john murphy
 *	
 *  This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 *  
 *  This class is used to capture the different game Types that can be played
 *  
 */
@Entity
@Table(name="gametype") //name of table in database
public class GameType {
	
	@Id //indicate primary key
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int game_id;  //id of game
	

	@OneToMany (mappedBy = "gametype")
	private List<Gamification> gamification;

	private int gamepoints; //number of points attained for performing an activity
	private String gamename;//name of game
	

	public GameType() {
		super();
		// TODO Auto-generated constructor stub - used by JPA
	}

	@Override
	public String toString() {
		return "GameType [game_id=" + game_id + ", gamepoints=" + gamepoints
				+ ", gamename=" + gamename + "]";
	}

	//Getters & Setters
	public List<Gamification> getGamification() {
		return gamification;
	}

	
	public void setGamification(List<Gamification> gamification) {
		this.gamification = gamification;
	}
	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getGamepoints() {
		return gamepoints;
	}

	public void setGamepoints(int gamepoints) {
		this.gamepoints = gamepoints;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	
	
	
}


