package ie.cit.group3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author john Murphy
 * 
 * The Wunderground Weather API JSON format is: { current_observation: { <key:value> pairs I want to access}}
 * This class acts as a 'wrapper' to get to the data I am interested in 
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true) //This annotation tells Jackson to ignore fields in JSON file that are not called in this class.
public class WrapperWeather {
	
	@JsonProperty("current_observation")
	private Weather weather;
	
	public WrapperWeather() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WrapperWeather [weather=" + weather + "]";
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
