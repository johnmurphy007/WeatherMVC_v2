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
public class WrapperMongoWeather {
	
	@JsonProperty("current_observation")
	private WeatherMongo weatherMongo;
	
	public WrapperMongoWeather() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WrapperWeather [weather=" + weatherMongo + "]";
	}

	public WeatherMongo getWeatherMongo() {
		return weatherMongo;
	}

	public void setWeatherMongo(WeatherMongo weather) {
		this.weatherMongo = weather;
	}

}
