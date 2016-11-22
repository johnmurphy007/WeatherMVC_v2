package ie.cit.group3.entity;


import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author john Murphy
 * 
 * This class is annotated as an Entity to allow JPA/Hibernate to access it (and determine the table & attributes in the table).
 * 
 * This class captures data about the weather
 *	
 */

@JsonIgnoreProperties(ignoreUnknown = true) //This annotation tells Jackson to ignore fields in JSON file that are not called in this class.

@Entity
@Table(name="weather") //name of table in database
public class Weather {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //ensures that Auto Increment is compatible with underlying database AI implementation
	private int id;
		
	@DateTimeFormat (pattern="dd-MM-YYYY")
//	private Date datestamp;
	private String local_time_rfc822;   	//Date/time: e.g. Wed, 27 June 2012 17:27:14 -0700
	private String local_epoch;	// # seconds since 1/1/1970?
	private String weather;
	private float temp_c;
	private String relative_humidity;
	private float dewpoint_c;
	
	
	//Constructors:
//	public Weather(String commenttext) {
//		super();
//		this.commenttext = commenttext;
//	}

	public Weather() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Weather [id=" + id 
				+ ", local_time_rfc822=" + local_time_rfc822 + ", local_epoch="
				+ local_epoch + ", weather=" + weather + ", temp_c=" + temp_c
				+ ", relative_humidity=" + relative_humidity + ", dewpoint_c="
				+ dewpoint_c + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocal_time_rfc822() {
		return local_time_rfc822;
	}

	public void setLocal_time_rfc822(String local_time_rfc822) {
		this.local_time_rfc822 = local_time_rfc822;
	}

	public String getLocal_epoch() {
		return local_epoch;
	}

	public void setLocal_epoch(String local_epoch) {
		this.local_epoch = local_epoch;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public float getTemp_c() {
		return temp_c;
	}

	public void setTemp_c(float temp_c) {
		this.temp_c = temp_c;
	}

	public String getRelative_humidity() {
		return relative_humidity;
	}

	public void setRelative_humidity(String relative_humidity) {
		this.relative_humidity = relative_humidity;
	}

	public float getDewpoint_c() {
		return dewpoint_c;
	}

	public void setDewpoint_c(float dewpoint_c) {
		this.dewpoint_c = dewpoint_c;
	}

}
