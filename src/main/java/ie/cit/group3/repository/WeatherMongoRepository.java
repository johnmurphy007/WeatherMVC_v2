package ie.cit.group3.repository;

import java.util.List;

import ie.cit.group3.entity.Weather;
import ie.cit.group3.entity.WeatherMongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
* @author john murphy
* 
* This interface inherits
*
*/
@RepositoryRestResource(collectionResourceRel="mongoweather", path ="mongoweather") //this directs Spring MVC to create RESTful endpoints at /mongoweather
public interface WeatherMongoRepository extends MongoRepository<Weather,String> {
	

	
}
