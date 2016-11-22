package ie.cit.group3.controller;

//import java.WS.WSRequest;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import ie.cit.group3.domain.Authorities;
import ie.cit.group3.domain.ChObject;
import ie.cit.group3.domain.Image;
import ie.cit.group3.domain.Participant;
import ie.cit.group3.domain.Participation;
import ie.cit.group3.domain.Role;
import ie.cit.group3.entity.Comment;
import ie.cit.group3.entity.CommentFlag;
import ie.cit.group3.entity.CommentThumb;
import ie.cit.group3.entity.Crowdsourcing;
import ie.cit.group3.entity.CrowdsourcingFlag;
import ie.cit.group3.entity.Flagchoice;
import ie.cit.group3.entity.GameType;
import ie.cit.group3.entity.Gamification;
import ie.cit.group3.entity.TagName;
import ie.cit.group3.entity.Users;
import ie.cit.group3.entity.Weather;
import ie.cit.group3.entity.WeatherMongo;
import ie.cit.group3.entity.WrapperMongoWeather;
import ie.cit.group3.entity.WrapperWeather;
import ie.cit.group3.repository.WeatherMongoRepository;
import ie.cit.group3.repository.WeatherRepository;
import ie.cit.group3.service.AuthoritiesService;
import ie.cit.group3.service.ChObjectService;
import ie.cit.group3.service.CommentFlagService;
import ie.cit.group3.service.CommentService;
import ie.cit.group3.service.CommentThumbService;
import ie.cit.group3.service.CrowdsourceFlagService;
import ie.cit.group3.service.CrowdsourcingService;
import ie.cit.group3.service.FlagchoiceService;
import ie.cit.group3.service.GameTypeService;
import ie.cit.group3.service.GamificationService;
import ie.cit.group3.service.ImageService;
import ie.cit.group3.service.JPAChObjectService;
import ie.cit.group3.service.ParticipantService;
import ie.cit.group3.service.ParticipationService;
import ie.cit.group3.service.RolesService;
import ie.cit.group3.service.TagNameService;
import ie.cit.group3.service.UserService;
import ie.cit.group3.service.UsersService;
import ie.cit.group3.utility.GeneralFormBackingBean;
import ie.cit.group3.utility.ImportJsonFiles;
import ie.cit.group3.utility.SendEMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.User;

import com.mongodb.Mongo;
@PropertySource("classpath:mail.properties")
//@RestController //(shorthand for @Controller and @ResponseBody)
@Controller //Indicates class is a controller
@RequestMapping(value={"/weather"})  //@RequestMapping("/")
public class WeatherController {
	/**
	 * @author John Murphy
	 * 
	 * This class handles all other non-"/browse" URL requests.
	 * 
	 * This class interfaces with the dispatchServlet to handles its URL requests and serves it the data (ModelMap) and html file to use to 
	 * display the data.
	 */

	/*
	 * Note: Started with Repository access objects.  Recently upgraded these to service access objects, but leaving original Repository access
	 * objects in-situ (just commented them out) as it is close to assignment handup and weary about making too many last minute adjustments
	 *
	 */
	@Autowired
    SmtpMailSender smtpMailSender;
	
	
	@Autowired
	SendEMail email;// = new SendEMail();

	@Autowired
	DataSource datasource;  //used to access dB for new Users.

	@Autowired
	ImageService imageService;			//We can now reference the object jdbcTemplate anywhere in this class


	//	UserRepository userRepository;
	@Autowired
	UserService userRepository;


	//	UsersRepository usersRepoPassword;
	@Autowired
	UsersService usersRepoPassword;


	@Autowired
	AuthoritiesService authoritiesRepository;

	@DateTimeFormat (pattern="dd-MM-YYYY")
	Date date ;	

// Change to Service level later when working
	@Autowired
	WeatherRepository weatherRepository;
	
	// Change to Service level later when working
		@Autowired
		WeatherMongoRepository weatherMongoRepository;

	/**
	 * @author john murphy
	 * 
	 * showHomePage: This method captures the /, /home URL. 
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return weatherhome.html as view
	 */
	//@RequestMapping(value={"/","/home"}, method = RequestMethod.GET) 
	@RequestMapping(value={"/","/weather"}) //firstly, identifies showHomPage() as a request handling method. It also specifies to use the method for '/' or '/home'
	public String showWeatherHomePage(ModelMap model) { //ModelMap represents the model which the data thats passed between controller and view
		
		List<Weather> weatherlist = (List<Weather>) weatherRepository.findAll();
		model.addAttribute("weather", weatherlist);
		return "weatherhome"; //returns the View name
	} 
   
//	@Scheduled(fixedRate = 30000) //job scheduled to run every 5000 ms (5 seconds)
    public void reportCurrentTime() {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        //System.out.println("The time is now " + dateFormat.format(new Date()));
	//	Weather weather = weatherRepository.findOne(1);

	//	email.SendEmail(weather);
    }
	
	/**
	 * @author john murphy
	 * 
	 * importObjects: This method captures the /import URL. Used to show page that user can enter directory for chobjects.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return import.html as view
	 */
	@RequestMapping(value="/current", method = RequestMethod.GET) 
	public String getCurrentWeatherData(ModelMap model) {			
		
		//RestTemplate makes interacting with RESTful services easy. It can even bind that data to customer domain types.
		RestTemplate restTemplate = new RestTemplate();
		//RestTemplate will use the Jackson library to convert the incoming JSON data into a weather object
        WrapperWeather wrapperweather = restTemplate.getForObject("http://api.wunderground.com/api/c28903546e3c74b3/geolookup/conditions/q/Ireland/Cork.json", WrapperWeather.class);
        
        Weather weather = wrapperweather.getWeather();
        weatherRepository.save(weather);
        //       log.info(weather.toString());
  //      WeatherMongo

    System.out.println(weather);


		model.addAttribute("weather",weather);
		return "weatherhome";			 
	}  
	
	@Scheduled(fixedRate = 3600000) //job scheduled to run every hour
	public void schedulereporsav()
	{
		//RestTemplate makes interacting with RESTful services easy. It can even bind that data to customer domain types.
		RestTemplate restTemplate = new RestTemplate();
		
		//Save to MongodB
		//RestTemplate will use the Jackson library to convert the incoming JSON data into a weather object
	//	WrapperMongoWeather wrappermongoweather = restTemplate.getForObject("http://api.wunderground.com/api/c28903546e3c74b3/geolookup/conditions/q/ORK.json", WrapperMongoWeather.class);
	//	WeatherMongo weathermongo1 = wrappermongoweather.getWeatherMongo();
	//	weatherMongoRepository.save(weathermongo1);
		
		//Save to MySQL dB
		WrapperWeather wrapperweather = restTemplate.getForObject("http://api.wunderground.com/api/c28903546e3c74b3/geolookup/conditions/q/ORK.json", WrapperWeather.class);
        Weather weather = wrapperweather.getWeather();
        weatherRepository.save(weather);
        weatherMongoRepository.save(weather);
 
    //	List<Weather> weatherlist = (List<Weather>) weatherRepository.findAll();
    	//Weather weather = (List<Weather>) weatherRepository.findAll();
	//	smtpMailSender.send("johnmurphy007@gmail.com","Hourly Weather update for Cork", weather.toString());
        
	}
	
	@Scheduled(fixedRate = 86400000) //job scheduled to once a day
	public void scheduleMailsav()
	{	
		List<Weather> weatherlist = new ArrayList<Weather>();
		
		for (int i = 1; i<=24; i++)
		{
			weatherlist.add(weatherRepository.findOne((int) weatherRepository.count()-i));
		}
		
	smtpMailSender.send("johnmurphy007@gmail.com","Hourly Weather update for Cork", weatherlist.toString());
    	
	}
	
	@RequestMapping("/mail")
	public String sendMail(ModelMap model) throws MessagingException {
		List<Weather> weatherlist = (List<Weather>) weatherRepository.findAll();	
		smtpMailSender.send("johnmurphy007@gmail.com","Weather Update for Cork", "Your request for weather data: "+ weatherlist);
		
		return "weatherhome";
	}
	
	@RequestMapping(value="/httprequest", method = RequestMethod.GET) 
	public String getCurrentWeatherDataHttpRequest(ModelMap model) {		
	
/*	 try {

		 HttpClient client = HttpClientBuilder.create().build();
		 HttpGet request = new HttpGet("http://mkyong.com");
		 HttpResponse response = client.execute(request);

		 System.out.println("Printing Response Header...\n");

		 Header[] headers = response.getAllHeaders();
		 for (Header header : headers) {
		 System.out.println("Key : " + header.getName() 
		                           + " ,Value : " + header.getValue());

		 }

		 System.out.println("\nGet Response Header By Key ...\n");
		 String server = response.getFirstHeader("Server").getValue();

		 if (server == null) {
		 System.out.println("Key 'Server' is not found!");
		 } else {
		 System.out.println("Server - " + server);
		 }

		 System.out.println("\n Done");

		    } catch (Exception e) {
		 e.printStackTrace();
		    }
	 */
	 //option 2
	    try {

	    	URL obj = new URL("http://api.wunderground.com/api/c28903546e3c74b3/geolookup/conditions/q/ORK.json");
	    	URLConnection conn = obj.openConnection();
	    	Map<String, List<String>> map = conn.getHeaderFields();

	    	System.out.println("Printing Response Header...\n");

	   // 	String weatherinfo = conn.guessContentTypeFromStream(null);
	   // 	System.out.println(weatherinfo);
	    	
	    	for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	    		System.out.println("Key : " + entry.getKey() 
	                               + " ,Value : " + entry.getValue());
	    	}

	    	System.out.println("\nGet Response Header By Key ...\n");
	    	String server = conn.getHeaderField("Server");

	    	if (server == null) {
	    		System.out.println("Key 'Server' is not found!");
	    	} else {
	    		System.out.println("Server - " + server);
	    	}

	    	System.out.println("\n Done");

	        } catch (Exception e) {
	    	e.printStackTrace();
	        }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		//model.addAttribute("weather",weather);
		return "weatherhome";	
	}
//    @RequestMapping(value="/mongo", method=RequestMethod.POST, consumes="application/json")
    //@RequestBody = tkae the request body and map it to a parameter in a Java method.
//    public WeatherMongo create(@RequestBody WeatherMongo weatherMongo) {
//      return weatherMongoRepository.save(weatherMongo);
//    }
	
	@RequestMapping(value="/mongo", method = RequestMethod.GET) 
	public String getCurrentWeatherDataForMongo(ModelMap model) {			
		
		Mongo mongo;
		SimpleMongoDbFactory factory;
		MongoTemplate template;
		try {
			mongo = new Mongo("127.0.0.1", 27017);
			factory = new SimpleMongoDbFactory(mongo, "test");
			template = new MongoTemplate(factory);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		//RestTemplate makes interacting with RESTful services easy. It can even bind that data to customer domain types.
		RestTemplate restTemplate = new RestTemplate();
		//RestTemplate will use the Jackson library to convert the incoming JSON data into a weather object
        WrapperWeather wrapperweather = restTemplate.getForObject("http://api.wunderground.com/api/c28903546e3c74b3/geolookup/conditions/q/Ireland/Cork.json", WrapperWeather.class);
        
        Weather weather = wrapperweather.getWeather();
        weatherMongoRepository.save(weather);

     
        System.out.println(weather);

		model.addAttribute("weather",weather);
		return "weatherhome";			 
	}  
// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	
	// =========================================================================================================================================	

	/**
	 * @author john murphy
	 * 
	 * importObjects: This method captures the /import URL. Used to show page that user can enter directory for chobjects.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return import.html as view
	 */
	@RequestMapping(value="/import", method = RequestMethod.GET) 
	public String importObjects(ModelMap model) {			

		//Create an empty form backing bean that will be used to get the user input for the directory location of JSON files.
		GeneralFormBackingBean directory = new GeneralFormBackingBean();

		model.addAttribute("directory",directory);
		return "import";			 
	}  

	/**
	 * @author john murphy
	 * 
	 * importObjectsDirectory: This method captures the /import POST URL requests. It extracts the directory location of JSON fiels and then imports the files
	 * from this location.
	 * RequestMethod.POST
	 * @param ModelMap model, GeneralFormBackingBean directory
	 * @return import.html as view
	 */
	@RequestMapping(value="/import", method = RequestMethod.POST) 
	public String importObjectsDirectory(@ModelAttribute ("directory") GeneralFormBackingBean directory, ModelMap model) {			

	//	System.out.println(directory);
//		ImportJsonFiles importJSON = new ImportJsonFiles(directory.getString1(), chobject, imageService, participants, participations,roles );
		String message = "JSON Files imported from "+directory.getString1();
		model.addAttribute("message", message);
		return "import";			 
	}  

	/**
	 * @author john murphy
	 * 
	 * UserHomePageNoId: This method captures the /homepage URL requests. It extracts the directory location of JSON fiels and then imports the files
	 * from this location.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return userHomePage.html as view
	 */
	@RequestMapping(value="/homepage", method = RequestMethod.GET) 
	public String UserHomePageNoId(ModelMap model)  
	{			
		//Get username (if available...i.e. logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Users u = userRepository.findByUsername(username);

		model.addAttribute("user", u);

		return "userHomePage";			
	}  

	/**
	 * @author john murphy
	 * 
	 * editProfile: This method captures the /editprofile URL requests. It directs the user to the edituserprofile page, and populates this page
	 * with the info for the currenlty logged in user.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return edituserprofile.html as view
	 */
	@RequestMapping(value="/editprofile", method = RequestMethod.GET) 
	public String editProfile(ModelMap model) 
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Users u = userRepository.findByUsername(username);

		model.addAttribute("user", u);
		return "edituserprofile";			
	}  

	/**
	 * @author john murphy
	 * 
	 * editProfilePost: This method captures the /editprofile URL requests. It saves the edited user details to the repository
	 * RequestMethod.POST
	 * @param ModelMap model, Users users
	 * @return edituserprofile.html as view
	 */
	@RequestMapping(value="/editprofile", method = RequestMethod.POST) 
	public String editProfilePost(@ModelAttribute ("users") Users users,ModelMap model)
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Users u = userRepository.findByUsername(username);

		String message = null;  //will be used to capture info on what has been updated by the user (and will be echoed back to the user)

		if (!u.getAddress1().equals(users.getAddress1()))  //if a change to Address1
		{
			u.setAddress1(users.getAddress1());
			message = 	"The following User profile details have been modified:\n";
			message += "Address Line 1";
		}
		if (!u.getAddress2().equals(users.getAddress2())) //if a change to Address2
		{
			u.setAddress2(users.getAddress2());
			if (message == null)
			{
				message = 	"The following User profile details have been modified:\n";
				message += "Address Line 2";
			}
			else
				message += "\n, Address Line 2";
		}
		if (!u.getAddress3().equals(users.getAddress3())) //if change to address3
		{
			u.setAddress3(users.getAddress3());
			if (message == null)
			{
				message = 	"The following User profile details have been modified:\n";
				message += "Address Line 3";
			}
			else
				message += "\n, Address Line 3";
		}
		if (!u.getEmail().equals(users.getEmail()))  //if change to email
		{
			u.setEmail(users.getEmail());
			if (message == null)
			{
				message = 	"The following User profile details have been modified:\n";
				message += "Email Address";
			}
			else
				message += "\n, Email Address";
		}
		if (u.getAge() != users.getAge())
		{
			u.setAge(users.getAge());
			if (message == null)
			{
				message = 	"The following User profile details have been modified:\n";
				message += "Age";
			}
			else
				message += "\n, Age";
		}


		userRepository.save(u);   //save info to repository

		model.addAttribute("message", message );
		model.addAttribute("user", u);
		
		return "edituserprofile";			
	}  

	/**
	 * @author john murphy
	 * 
	 * viewLeague: This method captures the /league URL requests. This method shows the league points of the top ten users and the league position
	 * the currently logged in user is at.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return leaguetable.html as view
	 */
	@RequestMapping(value="/league", method = RequestMethod.GET) 
	public String viewLeague(ModelMap model) 
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Users u = userRepository.findByUsername(username);  //get user details from repo.


		model.addAttribute("user", u);
		
		return "leaguetable";			
	}  

	/**
	 * @author John Stevens
	 * 
	 * viewGamePoints: This method captures the /league/viewpoints URL requests. This method shows the league points available for each game that can be 
	 * played. 
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return gametypetable.html as view
	 */
	@RequestMapping(value="/league/viewpoints", method = RequestMethod.GET) 
	public String viewGamePoints(ModelMap model) 
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
		
//		List<GameType> listgametype = (List<GameType>) gameTypeRepository.findAll();  //retrieve list of all gametypes from repo.

		//retrieve currently logged in users game points
		int countgamepoints = 0;

		model.addAttribute("user", u);
		model.addAttribute("usergamepoint",countgamepoints);
		
		return "gametypetable";			
	}  


	/**
	 * @author John Stevens
	 * 
	 * viewAdminGamePoints: This method captures the /league/gametype/viewpoints URL requests. This method shows the league points available for each game that can be 
	 * played. It allows the admin to select an individual game type entry for editing.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return admingametypetable.html as view 
	 * */
	@RequestMapping(value="/admin/gametype/viewpoints", method = RequestMethod.GET) 
	public String viewAdminGamePoints(ModelMap model) 
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

//		List<GameType> listgametype = (List<GameType>) gameTypeRepository.findAll();


		Users u = userRepository.findByUsername(username);

		//retrieve currently logged in users game points
		int countgamepoints = 0;

		model.addAttribute("user", u);
		model.addAttribute("usergamepoint",countgamepoints);
		
		return "admingametypetable";			
	}  
	/**
	 * @author John Stevens
	 * 
	 * getGamePoints: This method captures the /league/gametype/viewpoints/{id} URL requests. This method shows the league points available for the 
	 * selected gametype. The admin can edit this game type entry.
	 * RequestMethod.GET
	 * @param ModelMap model, int id
	 * @return adminEditgametypetable.html as view 
	 */
	@RequestMapping(value="/admin/gametype/viewpoints/{id}", method = RequestMethod.GET) 
	public String getGamePoints(@PathVariable int id, ModelMap model)
	{
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);

		//retrieve currently logged in users game points
		int countgamepoints = 0;

		model.addAttribute("user", u);
		model.addAttribute("usergamepoint",countgamepoints);

		return "adminEditgametypetable";
	}

	/**
	 * @author John Stevens
	 * 
	 * getGamePoints: This method captures the /league/gametype/viewpoints/{id} POST URL requests. This method updates the edited gametype.
	 * RequestMethod.POST
	 * @param ModelMap model, int id
	 * @return adminEditgametypetable.html as view 
	 */
	@RequestMapping(value="/admin/gametype/viewpoints/{id}", method = RequestMethod.POST) 
	public String setGamePoints(@ModelAttribute ("gametype") GameType gametype, @PathVariable int id,	ModelMap model) 
	{	
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);

		//retrieve currently logged in users game points
		int countgamepoints = 0;

	
		model.addAttribute("user", u);
		model.addAttribute("usergamepoint",countgamepoints);
	
		return "admingametypetable";			
	}  
	
	/**
	 * @author john murphy
	 * 
	 * viewGamificationHistory: This method captures the /gamification URL requests. It outputs a complete list of the gamification points activity the 
	 * currently logged in user has undertaken.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return gamificationhistory.html as view 
	 */
	@RequestMapping(value="/gamification", method = RequestMethod.GET) 
	public String viewGamificationHistory(ModelMap model)  //potentially changing this to int (for int id of User)
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);

		return "gamificationhistory";			
	}  

	
	
	/**
	 * @author john murphy
	 * 
	 * AdminListOneUser: This method captures the /admin/listallusers/{id} URL requests. 
	 * This method allows the admin to edit user status/authority
	 * RequestMethod.GET
	 * @param ModelMap model, int id
	 * @return adminedituserprofile.html as view 
	 */
	@RequestMapping(value="/admin/listallusers/{id}", method = RequestMethod.GET) 
	public String AdminListOneUser(@PathVariable int id, ModelMap model) 
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
	
		Users editUser = userRepository.findOne(id);
	

		//Get List of Distinct Authorities
		List<Authorities> authorities = authoritiesRepository.findDistinctAuthorities();
	

		model.addAttribute("authorities",authorities);  //List of Authorities is displayed in drop down list in html
		model.addAttribute("user", u);
		model.addAttribute("edituser",editUser);

		return "adminedituserprofile";			
	} 

	/**
	 * @author john murphy
	 * 
	 * AdminListAllUsersPost: This method captures the /admin/listallusers/{id} POST URL requests. 
	 * This method allows the admin to edit user status/authority
	 * RequestMethod.POST
	 * @param ModelMap model, int id, Users edituser
	 * @return adminedituserprofile.html as view 
	 */
	@RequestMapping(value="/admin/listallusers/{id}", method = RequestMethod.POST) 
	public String AdminListAllUsersPost(@ModelAttribute ("edituser") Users edituser, @PathVariable int id, ModelMap model)  //potentially changing this to int (for int id of User)
	{																	//Kinda guessing that you just want a singular update here.
		//get currently logged in user details.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
	
		String message ="";
		Users originaluser = userRepository.findByUsername(edituser.getUsername());
	
		//Check is any change in user details in repository and what has come back from Admin page.
		if (!originaluser.getAddress1().equals(edituser.getAddress1()) || !originaluser.getAddress2().equals(edituser.getAddress2()) ||
				!originaluser.getAddress3().equals(edituser.getAddress3()) || originaluser.getAge()!=edituser.getAge() ||
				!originaluser.getDatejoined().equals(edituser.getDatejoined()) || !originaluser.getEmail().equals(edituser.getEmail()))
			message = "User Details updated on System";

		//Update repository with info that Admin changed
		userRepository.save(edituser);

		//Update repository 'users' that contains: username, password, enabled
		ie.cit.group3.domain.User updateUser = usersRepoPassword.get(edituser.getUsername());
	
		if (updateUser.isEnabled() != edituser.isAccountstatus())
		{
			updateUser.setEnabled(edituser.isAccountstatus());
			usersRepoPassword.save(updateUser);
			message = "User Details updated on System";
		}
		
		//Update repository that contains Authorities(username, authority)
		Authorities authority = authoritiesRepository.get(edituser.getUsername());
		System.out.println("User Authorities etc = "+authority);
		if (!authority.getAuthority().equals(edituser.getAuthority()))
		{
			authority.setAuthority(edituser.getAuthority());
			authoritiesRepository.save(authority);
			message = "User Details updated on System";
		}
		
		//Get List of Distinct Authorities
		List<Authorities> authorities = authoritiesRepository.findDistinctAuthorities();
		

		model.addAttribute("authorities",authorities);
		model.addAttribute("user", u);
		model.addAttribute("edituser",edituser);
		model.addAttribute("message", message );

		return "adminedituserprofile";			
	} 


	
	
	/**
	 * @author john murphy
	 * 
	 * AdminFlaggedCommentsReviewed: This method captures the /admin/flaggedcomments/reviewed/{commentrow}/{decision} URL requests. 
	 * It updates the repository with the disposition decision made by the administrator for the flagged comment.  Then generates a new
	 * list of un-reviewed flagged comments and outputs this to view.
	 * RequestMethod.GET
	 * @param ModelMap model, int commentrow, String decision
	 * @return adminflaggedcomments.html as view 
	 */
	@RequestMapping(value="/admin/flaggedcomments/reviewed/{commentrow}/{decision}", method = RequestMethod.GET) 
	public String AdminFlaggedCommentsReviewed(@PathVariable int commentrow, @PathVariable String decision, ModelMap model) 
	{			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
	

		//Retrieve the Rows where the comments are being reviewed by Admin
//		List<CommentFlag> commentFlags = commentFlagRepository.findByAdminreviewedIsFalse();
		model.addAttribute("user", u);

		return "adminflaggedcomments";			
	} 

	/**
	 * @author john murphy
	 * 
	 * AdminFlaggedDescriptions: This method captures the /admin/flaggeddescriptions URL requests. 
	 * This method retrieves the list of flagged Crowdsourcing Descriptions that have not been reviewed by the admin.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return adminflaggeddescriptions.html as view 
	 */
	@RequestMapping(value="/admin/flaggeddescriptions", method = RequestMethod.GET) 
	public String AdminFlaggedDescriptions(ModelMap model)  //potentially changing this to int (for int id of User)
	{			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);


		return "adminflaggeddescriptions";			
	} 

	/**
	 * @author john murphy
	 * 
	 * AdminFlaggedDescriptionsReviewed: This method captures the /admin/flaggeddescriptionsreviewed/{commentrow}/{decision} URL requests. 
	 * It updates the repository with the disposition decision made by the administrator for the flagged crowdsourced descriptions.  Then generates a new
	 * list of un-reviewed flagged descriptions and outputs this to view.
	 * RequestMethod.GET
	 * @param ModelMap model, int commentrow, String decision
	 * @return adminflaggeddescriptions.html as view 
	 */
	@RequestMapping(value="/admin/flaggeddescriptions/reviewed/{commentrow}/{decision}", method = RequestMethod.GET) 
	public String AdminFlaggedDescriptionsReviewed(@PathVariable int commentrow, @PathVariable String decision, ModelMap model)  //potentially changing this to int (for int id of User)
	{			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
	


		return "adminflaggeddescriptions";			
	} 

	/**
	 * @author john murphy
	 * 
	 * getNewUser: This method captures the /newuser URL requests. Provides a blank users object to the view.
	 * RequestMethod.GET
	 * @param ModelMap model, Users users
	 * @return newuser.html as view 
	 */
	@RequestMapping(value="/newuser", method = RequestMethod.GET) 
	public ModelAndView getNewUser(ModelMap model) {			

		return new ModelAndView ("newuser", "users", new Users());			
	}  

	/**
	 * @author john murphy
	 * 
	 * getNewUser: This method captures the /newuser URL POST requests. Handles the completed users object.  It encrypts the user password
	 * and deletes the text password.  It updates the username into the 'user' and 'users' table and the usernames authority to the 'authorities' table
	 * RequestMethod.POST
	 * @param ModelMap model, Users users
	 * @exception Exception
	 * @return welcome.html as view 
	 */
	@RequestMapping(value="/newuser", method = RequestMethod.POST) 
	public String postNewUser(@ModelAttribute ("users") Users users,ModelMap model) throws Exception {			

		//start of security 
		JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager(); //create new instance of UserDetailsService() provided by Spring.
		userDetailsService.setDataSource(datasource); //associate a datasource to the UserDetailsService()
		PasswordEncoder encoder = new BCryptPasswordEncoder();	//Used to store passwords securely
		
		if(!userDetailsService.userExists(users.getUsername())) //test if user "user" exists and creates one if not.
		{ 
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); //list entries in authorities table in dB
			authorities.add(new SimpleGrantedAuthority("USER"));
			User userDetails = new User(users.getUsername(), encoder.encode(users.getPassword()), authorities);

			userDetailsService.createUser(userDetails);

			//end copy security
			users.setPassword(""); //Password is saved as an encrypted element in User table. This field is used just to get user input (form backing bean).
			userRepository.save(users);
		}

		model.addAttribute("user",users);
		//look to save to repo
		return "welcome";			//or could change this to go straight to users home page
	}


	/**
	 * @author john murphy
	 * 
	 * getNewUser: This method captures the /object/{id} URL POST requests. It handles all POST requests from the numerous forms within the 'displayItem' html.
	 * RequestMethod.POST
	 * @param ModelMap model, Comment comment, TagName tagname, Crowdsourcing crowdsourcing, CommentFlag commentFlag, String id
	 * @return displayItem.html as view 
	 */
	@RequestMapping(value="/object/{id}", method = RequestMethod.POST) 
	public String GetCHObject(@ModelAttribute ("comment") Comment comment, 
			@ModelAttribute ("tagname") TagName tagname, 
			@ModelAttribute ("crowdsourcing") Crowdsourcing crowdsourcing,
			@ModelAttribute ("commentFlag") CommentFlag commentFlag,
			@PathVariable String id, ModelMap model) {			

		date = new Date();  //get todays date. User to time/date stamp the activity.
	

		//Get logged in user details
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		return "displayItem";			
	}  


	/**
	 * @author john murphy
	 * 
	 * ShowObject: This method captures the /object/{id} URL requests. This methods generates the data that is displayed for an object.
	 * RequestMethod.GET
	 * @param ModelMap model, String id
	 * @return displayItem.html as view 
	 */
	@RequestMapping(value="/object/{id}", method = RequestMethod.GET) 
	public String ShowObject(@PathVariable String id, ModelMap model) {			


		return "displayItem";					
	}  

	
	/**
	 * @author john murphy
	 * 
	 * FlagMethod: This method captures the /object/{id}/flag POST URL requests. This methods deals with updating the CommentFlag
	 * RequestMethod.POST
	 * @param ModelMap model, String id, CommentFlag commentFlag
	 * @return displayItem.html as view 
	 */
	@RequestMapping(value="/object/{id}/flag", method = RequestMethod.POST) 
	public String FlagMethod(@PathVariable String id, ModelMap model,
			@ModelAttribute ("commentFlag") CommentFlag commentFlag) {			


		return "displayItem";			
	}  
	
	/**
	 * @author john murphy
	 * 
	 * ShowThumbUpDown: This method captures the /object/{id}/{commentid}/{thumb} URL requests. This methods deals with updating the ThumbUp ThumbDown 
	 * info for an object.
	 * RequestMethod.GET
	 * @param ModelMap model, String id, int commentid, String thumb
	 * @return displayItem.html as view 
	 */
	@RequestMapping(value="/object/{id}/{commentid}/{thumb}", method = RequestMethod.GET) 
	public String ShowThumbUpDown(@PathVariable String id, @PathVariable int commentid,
			@PathVariable String thumb, ModelMap model) {			


		return "displayItem";			
		
	}  

} //end of class





