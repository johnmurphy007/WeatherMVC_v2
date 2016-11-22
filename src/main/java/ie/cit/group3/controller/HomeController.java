package ie.cit.group3.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.User;


@Controller //Indicates class is a controller
@RequestMapping(value={"/","/home"})  //@RequestMapping("/")
public class HomeController {
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
	DataSource datasource;  //used to access dB for new Users.

	@Autowired
	ImageService imageService;			//We can now reference the object jdbcTemplate anywhere in this class

	//	@Autowired
	//	CommentRepository commentRepository;		
	@Autowired
	CommentService commentRepository;		


	//	@Autowired
	//	CommentThumbRepository ctRepository;
	@Autowired
	CommentThumbService ctRepository;

	@Autowired
	RolesService roles;

	@Autowired
	ParticipantService participants;

	@Autowired
	ParticipationService participations;

	//	@Autowired
	//	ImageRepository images;  //changed to Repository (from Service 5/5/2015...change back once debugged)

	//	@Autowired
	//	CrowdsourcingRepository crowdsourcingRepository;
	@Autowired
	CrowdsourcingService crowdsourcingRepository;

	//	@Autowired
	//	UserRepository userRepository;
	@Autowired
	UserService userRepository;

	//	@Autowired
	//	GamificationRepository gameRepository;
	@Autowired
	GamificationService gameRepository;

	//	@Autowired
	//	GameTypeRepository gameTypeRepository;
	@Autowired
	GameTypeService gameTypeRepository;

	//	@Autowired
	//	TagNameRepository tagnameRepository;
	@Autowired
	TagNameService tagnameRepository;

	//	@Autowired
	//	CommentFlagRepository commentFlagRepository;
	@Autowired
	CommentFlagService commentFlagRepository;

	//	@Autowired
	//	UsersRepository usersRepoPassword;
	@Autowired
	UsersService usersRepoPassword;

	//	@Autowired
	//	AuthoritiesRepository authoritiesRepository;
	@Autowired
	AuthoritiesService authoritiesRepository;

	//	@Autowired
	//	FlagchoiceRepository flagchoiceRepository;
	@Autowired
	FlagchoiceService flagchoiceRepository;

	//	@Autowired
	//	CrowdsourceFlagRepository crowdFlagRepository;
	@Autowired
	CrowdsourceFlagService crowdFlagRepository;

	@DateTimeFormat (pattern="dd-MM-YYYY")
	Date date ;	
	//	@Autowired
	//	ChObjectRepository chobject; //changed to Repository (from Service 5/5/2015...change back once debugged)
	@Autowired
	ChObjectService chobject; 

	//	@Autowired
	//	JPAChObjectRepository jpaChobject;
	@Autowired
	JPAChObjectService jpaChobject;    

	/**
	 * @author john murphy
	 * 
	 * showHomePage: This method captures the /, /home URL. 
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return home.html as view
	 */
	//@RequestMapping(value={"/","/home"}, method = RequestMethod.GET) 
	@RequestMapping(value={"/","/home"}) //firstly, identifies showHomPage() as a request handling method. It also specifies to use the method for '/' or '/home'
	public String showHomePage(ModelMap model) { //ModelMap represents the model which the data thats passed between controller and view


		return "home"; //returns the View name
	} 

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
		ImportJsonFiles importJSON = new ImportJsonFiles(directory.getString1(), chobject, imageService, participants, participations,roles );
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

		List<Gamification> gmTop10 = gameRepository.findTopTen(); //get top ten league points details
		if (gmTop10 !=null)
		{
			for (int i = 0; i < gmTop10.size(); i++) 
				System.out.println(gmTop10.get(i));
		}
	
		//get the points for the current logged in user:
		int countgamepoints = 0;
		if (gameRepository.countByGamificationByUser(u.getId())>0)
			countgamepoints = gameRepository.findBySumGamePointsByUser(u.getId());
		
		//get position for the current logged in user:
		List<Gamification> gmAllUsers = gameRepository.findAllUsersPositions();
		int usergamepointsposition = 0;
		if (gmAllUsers != null)
		{
			for (int i = 0; i < gmAllUsers.size(); i++)
			{
				if (gmAllUsers.get(i).getUser().getId() == u.getId())
				{
					usergamepointsposition = i+1;
					i = gmAllUsers.size(); //to exit for loop quickly
				}
			}
		}


		model.addAttribute("userposition", usergamepointsposition);
		model.addAttribute("user", u);
		model.addAttribute("topten",gmTop10);
		model.addAttribute("usergamepoint",countgamepoints);
		
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
		
		List<GameType> listgametype = (List<GameType>) gameTypeRepository.findAll();  //retrieve list of all gametypes from repo.

		//retrieve currently logged in users game points
		int countgamepoints = 0;
		if (gameRepository.countByGamificationByUser(u.getId())>0)
			countgamepoints = gameRepository.findBySumGamePointsByUser(u.getId());

		model.addAttribute("user", u);
		model.addAttribute("gametype", listgametype);
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

		List<GameType> listgametype = (List<GameType>) gameTypeRepository.findAll();


		Users u = userRepository.findByUsername(username);

		//retrieve currently logged in users game points
		int countgamepoints = 0;
		if (gameRepository.countByGamificationByUser(u.getId())>0)
			countgamepoints = gameRepository.findBySumGamePointsByUser(u.getId());

		model.addAttribute("user", u);
		model.addAttribute("gametype", listgametype);
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
		if (gameRepository.countByGamificationByUser(u.getId())>0)
			countgamepoints = gameRepository.findBySumGamePointsByUser(u.getId());

		GameType gt = gameTypeRepository.findOne(id);

		model.addAttribute("gametype", gt);
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
		if (gameRepository.countByGamificationByUser(u.getId())>0)
			countgamepoints = gameRepository.findBySumGamePointsByUser(u.getId());

	
		GameType gt = gameTypeRepository.findOne(id); //get the original gametype entry.
		gt.setGamepoints(gametype.getGamepoints()); //update the original entry with the updated gamepoints.
		gameTypeRepository.save(gt); 	//SAVE THE GAMETYPE INFO TO REPO
		
		List<GameType> listgametype = (List<GameType>) gameTypeRepository.findAll();  //generate a list of gametypes

	
		model.addAttribute("user", u);
		model.addAttribute("gametype", listgametype);
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

		List<Gamification> gmlist = gameRepository.findByUser_id(u.getId());  //get the gamification history for currently logged in user.
	
		//get the points for the current logged in user:
		int countgamepoints = 0;
		if (gameRepository.countByGamificationByUser(u.getId())>0)
			countgamepoints = gameRepository.findBySumGamePointsByUser(u.getId());


		model.addAttribute("user", u);
		model.addAttribute("usergame",gmlist);
		model.addAttribute("usergamepoint",countgamepoints);

		return "gamificationhistory";			
	}  

	/**
	 * @author john murphy
	 * 
	 * AdminListAllUsers: This method captures the /admin/listallusers URL requests. 
	 * This method checks that the certain user data in user table is consistent with the certain data in table users and table authorities.
	 * Specifically it ensures that the 'enabled' status in table 'users' (login table that Spring likes to use) is matched with the 'accountstatus' in 
	 * table 'user'.  It also ensures that the 'authority' from table 'authorities' is matched with 'authority' in table 'user'.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return listallusers.html as view 
	 */
	@RequestMapping(value="/admin/listallusers", method = RequestMethod.GET) 
	public String AdminListAllUsers(ModelMap model) 
	{			
		//Get username info (if logged in)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
	
		List<ie.cit.group3.domain.User> listPasswords = usersRepoPassword.findAll(); //get the list of users from the 'encrypted passwords' user table.
		
		List<Authorities> authorities = authoritiesRepository.findAll(); //get the authorities for users.
		
		List<Users> listusers = userRepository.findAll();  //get users details.
		
		// Checking to ensure that the 'enabled' status in table 'users' (login table that Spring likes to use) is matched with the 'accountstatus' in 
		 // table 'user'.  It also ensures that the 'authority' from table 'authorities' is matched with 'authority' in table 'user'.
		for (int i=0; i< listusers.size(); i++) //cycle through the list of users (from table 'user').
		{
			for (int j = 0; j< listPasswords.size(); j++) //cycle through the list of users (from table 'users') and finds a match between them.
			{
				if (listPasswords.get(j).getUsername().equals(listusers.get(i).getUsername()))
					listusers.get(i).setAccountstatus(listPasswords.get(j).isEnabled());  //ensures 'enabled' status in table 'users' = 'accountstatus' in 
				 																			// table 'user'
			}
			for (int j = 0; j< authorities.size(); j++)
			{
				if (authorities.get(j).getUsername().equals(listusers.get(i).getUsername()))
					listusers.get(i).setAuthority(authorities.get(j).getAuthority()); //'authority' from table 'authorities' is matched with 'authority' in table 'user'.

			}
			userRepository.save(listusers.get(i)); //save any changes to repository.
		}


		model.addAttribute("user", u);
		model.addAttribute("listusers",listusers);


		return "listallusers";			
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
	 * AdminFlaggedComments: This method captures the /admin/flaggedcomments URL requests. 
	 * This method retrieves the list of flagged Comments that have not been reviewed by the admin.
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return adminflaggedcomments.html as view 
	 */
	@RequestMapping(value="/admin/flaggedcomments", method = RequestMethod.GET) 
	public String AdminFlaggedComments(ModelMap model)  
	{			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users u = userRepository.findByUsername(username);
		

		List<CommentFlag> commentFlags = commentFlagRepository.findByAdminreviewedIsFalse();  //get un-reviewed comments

		model.addAttribute("user", u);
		model.addAttribute("commentflags", commentFlags);

		return "adminflaggedcomments";			
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
		List<CommentFlag> commentFlags = commentFlagRepository.findByAdminreviewedIsFalse();
		CommentFlag commentFlagToUpdate = commentFlags.get(commentrow);
	
		commentFlagToUpdate.setAdminreviewed(true);  //indicates that the admin has reviewed this flagged item
		commentFlagRepository.save(commentFlagToUpdate); //save updated CommentFLag record to repo

		if (decision.equals("yes")) //means that the original comment is to be deleted
		{
			Comment comment = commentRepository.findOne(commentFlagToUpdate.getComment().getId()); //get original comment
			comment.setFlag(true); //Admin flag to uphold the complaint (used to remove the visibility of the comment) 
		}	

		commentFlags = commentFlagRepository.findByAdminreviewedIsFalse();  //refresh the list of Comment Flags that have not been reviewed by Admin

		model.addAttribute("user", u);
		model.addAttribute("commentflags", commentFlags);

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

		List<CrowdsourcingFlag> crowdsourcingFlags = crowdFlagRepository.findByAdminreviewedIsFalse();


		model.addAttribute("user", u);
		model.addAttribute("crowdsourcingFlags",crowdsourcingFlags);

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
	

		//Retrieve the Rows where the comments are being reviewed by Admin
		List<CrowdsourcingFlag> crowdsourcingFlags = crowdFlagRepository.findByAdminreviewedIsFalse();
		CrowdsourcingFlag descriptionFlagToUpdate = crowdsourcingFlags.get(commentrow);
		
		descriptionFlagToUpdate.setAdminreviewed(true);    //indicates that the admin has reviewed this flagged item
		crowdFlagRepository.save(descriptionFlagToUpdate); //save updated CommentFLag record to repo

		if (decision.equals("yes")) //means that the original comment is to be deleted
		{
			Crowdsourcing description = crowdsourcingRepository.findOne(descriptionFlagToUpdate.getCrowdsourcing().getId()); //get original description
			description.setFlag(true); //Admin flag to uphold the complaint (used to remove the visibility of the comment) 
		}	

		crowdsourcingFlags = crowdFlagRepository.findByAdminreviewedIsFalse(); //refresh the list of Comment Flags that have not been reviewed by Admin
		
		model.addAttribute("user", u);
		model.addAttribute("crowdsourcingFlags", crowdsourcingFlags);

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
	

		ChObject object = chobject.get(id); //retrieves the chobject with id from repo.

		//Generates the Participation info for this object
		List<Participation> part = participations.get(object);
		Role role = new Role();
		Participant participant = new Participant();

		if (part != null)
		{
			for (int i = 0; i < part.size(); i++)
			{
				participant = part.get(i).getParticipant();
				
				//next, populate participant with all records in participant (only id was entered before this)
				participant = participants.get(participant.getPerson_id()); 
				role = part.get(i).getRole();
				role = roles.get(role.getRole_id());
				
				//add populated role and participant back to participation
				part.get(i).setParticipant(participant);
				part.get(i).setRole(role);
			}
		}

		//Get an image for this object (if it exists).  If had more time would explore populating the 
		//object with all images using: map.put(key, value) & map.keySet() etc to recreate the original Map List of the object
		//and feed this through to html and use Thymeleaf to access it using: ${object.image.image_id} etc
		List<Image> chimages = imageService.find(id);
		Image image = new Image();
		int countimages = 0;
		if (chimages != null)
		{
			countimages = chimages.size();
			int i = 0;
			do
			{
				image = chimages.get(i);
				i++;
			} while (( i < chimages.size()) && (image.getUrl() != null));
		}

		//Get logged in user details
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();


		GameType gt;
		Gamification g;

		Users u;
		int usergamepoints = 0;

		if (username !=null)  //check to make sure user is logged in.
		{ 
			u = userRepository.findByUsername(username);

			gt = new GameType();

			//check if user has authority higher than 'USER' and that a Description exists for the Crowdsourcing description
			if (crowdsourcing.getDescription() != null && !u.getAuthority().equals("USER"))
			{
				crowdsourcing.setChobject_id(object.getId()); //set the chobject id for the item a desciption has been provided.
				crowdsourcing.setUser(u);			//update description with details of user that provided the update
				crowdsourcing.setDatestamp(date);  //update with todays date
				crowdsourcingRepository.save(crowdsourcing);	//save crowdsourcing object
			}


			if (comment != null)  
			{
				if (comment.getCommenttext() != null)  //check if a comment has been provided.
				{
					gt = gameTypeRepository.findByGamename("Comment on object"); //This is the relevant game for comments.


					//Create new Gamification object & update the attributes of the gameification object.
					g = new Gamification();
					g.setChobject_id(id);
					g.setDatestamp(date);
					g.setGamepoints(gt.getGamepoints());
					g.setUser(u);
					g.setGametype(gt);

					gameRepository.save(g);  //save gamification object to repo.
				
					//Update comment and save to repo:
					comment.setUser(u);
					comment.setChobject_id(id);
					comment.setDatestamp(date);
					comment.setGamification(g);

					commentRepository.save(comment);
				}
			}

			if (tagname != null)
			{
				if (tagname.getTagword() != null) //check if a tag words has been provided.
				{
					gt = gameTypeRepository.findByGamename("Tag");  //This is the relevant game for Tag words.

		
					//Create new Gamification object & update the attributes of the gameification object.
					g = new Gamification();
					g.setChobject_id(id);
					g.setDatestamp(date);
					g.setGamepoints(gt.getGamepoints());
					g.setUser(u);
					g.setGametype(gt);
	
					gameRepository.save(g); //save gamification object to repo.

					//Update tagname and save to repo:
					tagname.setUser(u);
					tagname.setChobject_id(id);
					tagname.setDatestamp(date);
					tagname.setGamification(g);

					tagnameRepository.save(tagname);
				}
			}
			
			//get and update user points that logged in user has earned
			if (gameRepository.countByGamificationByUser(u.getId())>0)
				usergamepoints = gameRepository.findBySumGamePointsByUser(u.getId());
		}
		
		
		comment = null;
		tagname = null;

		//get list of comments for selected object
		List<Comment> cs = commentRepository.findBychobject_idANDFlagFalse(id);

		//get list of tagwords for selected object
		List<TagName> tn = tagnameRepository.findBychobject_id(id);
		
		//get list of crowdsourced descriptions
		List<Crowdsourcing> crowds = crowdsourcingRepository.findBychobject_idANDflagFalse(id); //add crowdsourced descriptions for this chObject.
		
		//get list of complaint choices that user can select (Used for flaggin items)
		List<Flagchoice> flagchoices = flagchoiceRepository.findAll();

		
		//provide new form backing beans
		TagName t = new TagName();
		Comment c = new Comment();	
		Crowdsourcing cds = new Crowdsourcing();
		CommentFlag commentFlagnew = new CommentFlag();


		model.addAttribute("flagchoices",flagchoices);
		model.addAttribute("commentFlag", commentFlagnew);
		model.addAttribute("crowdsourcings", crowds);		
		model.addAttribute("crowdsourcing", cds);  // if adding 2 comments is not possible then uncomment this out.
		model.addAttribute("usergamepoints", usergamepoints);
		model.addAttribute("participations", part);
		model.addAttribute("comment", c);
		model.addAttribute("tagname",t);
		model.addAttribute("comments",cs);
		model.addAttribute("tagnames", tn);
		model.addAttribute("object", object);
		model.addAttribute("image", image);
		model.addAttribute("imageNo", countimages);

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

		
		ChObject object = chobject.get(id); //retrieves the chobject with id from repo.

		//Generates the Participation info for this object
		List<Participation> part = participations.get(object);
		Role role = new Role();
		Participant participant = new Participant();
		
		if (part != null)
		{
			for (int i = 0; i < part.size(); i++)
			{
			
				participant = part.get(i).getParticipant();
				
				//next, populate participant with all records in participant (only id was entered before this)
				participant = participants.get(participant.getPerson_id()); 
				role = part.get(i).getRole();
				role = roles.get(role.getRole_id());
				
				//add populated role and participant back to participation
				part.get(i).setParticipant(participant);
				part.get(i).setRole(role);
			}
		}

		//Get an image for this object (if it exists).  If had more time would explore populating the 
		//object with all images using: map.put(key, value) & map.keySet() etc to recreate the original Map List of the object
		//and feed this through to html and use Thymeleaf to access it using: ${object.image.image_id} etc
		List<Image> chimages = imageService.find(id); //retrieves the images for chobject id from repo
		Image image = new Image();
		int countimages = 0;
		if (chimages != null)
		{
			countimages = chimages.size();
			int i = 0;
			do
			{
				image = chimages.get(i);
				i++;
			} while (( i < chimages.size()) && (image.getUrl() != null));
		}

		//get list of comments for this object
		List<Comment> cs = commentRepository.findBychobject_idANDFlagFalse(id);

		//get list of tag words for this object
		List<TagName> tn = tagnameRepository.findBychobject_id(id);

		TagName t = new TagName();
		Comment c = new Comment();	

		//Get logged in user details
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Users u;
		int usergamepoints = 0;

		//check if user is logged in:
		if (!username.equals("anonymousUser"))
		{ 
			//get and update user points that logged in user has earned
			u = userRepository.findByUsername(username);
			if (gameRepository.countByGamificationByUser(u.getId())>0)
				usergamepoints = gameRepository.findBySumGamePointsByUser(u.getId());

		}
		
		//Get list of crowdsourced descriptions for this object
		List<Crowdsourcing> crowds = crowdsourcingRepository.findBychobject_idANDflagFalse(id); //add crowdsourced descriptions for this chObject.

		//get list of complaint choices that user can select (Used for flaggin items)
		List<Flagchoice> flagchoices = flagchoiceRepository.findAll();
		
		//Provide blank form back beans
		Crowdsourcing crowdsourcing = new Crowdsourcing();
		CommentFlag commentFlag = new CommentFlag();
	

		model.addAttribute("flagchoices",flagchoices);
		model.addAttribute("commentFlag", commentFlag);
		model.addAttribute("crowdsourcings", crowds);		
		model.addAttribute("crowdsourcing", crowdsourcing); 
		model.addAttribute("usergamepoints", usergamepoints);
		model.addAttribute("participations", part);
		model.addAttribute("imageNo", countimages);
		model.addAttribute("object", object);
		model.addAttribute("image",image);
		model.addAttribute("comments", cs);
		model.addAttribute("tagnames", tn);
		model.addAttribute("comment", c);
		model.addAttribute("tagname",t);

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


		ChObject object = chobject.get(id); //retrieves the chobject with id from repo.

		List<Participation> part = participations.get(object);
		Role role = new Role();
		Participant participant = new Participant();

		if (part != null)
		{
			for (int i = 0; i < part.size(); i++)
			{
				
				participant = part.get(i).getParticipant();
				
				//next, populate participant with all records in participant (only id was entered before this)
				participant = participants.get(participant.getPerson_id()); 
				role = part.get(i).getRole();
				role = roles.get(role.getRole_id());
				
				//add populated role and participant back to participation
				part.get(i).setParticipant(participant);
				part.get(i).setRole(role);
			}
		}

		List<Image> chimages = imageService.find(id); //retrieves the images for chobject id from repo


		Image image = new Image();
		int countimages = 0;
		if (chimages != null)
		{
			countimages = chimages.size();
			int i = 0;
			do
			{
				image = chimages.get(i);
				i++;

			} while (( i < chimages.size()) && (image.getUrl() != null));
		}

		//User that made the complaint is:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Users complainingUser = new Users();//look to change this!!!!!
		if (username !=null)
		{
			//Possible enclose all remaining code with this if statement??
			complainingUser = userRepository.findByUsername(username);
			commentFlag.setUser(complainingUser);
		}
		date = new Date();

		//Get list of comments and Crowdsourcing for the chobject id:
		List<Comment> cs = commentRepository.findBychobject_idANDFlagFalse(id);  //gets all the comments for a ChObject
		List<Crowdsourcing> crowds = crowdsourcingRepository.findBychobject_idANDflagFalse(id); //gets all crowdsourced descriptions for this chObject.

		//Identify the comment that was flagged by the user
		//Crowdsourcing Comments Flag Section
		if(commentFlag.getFlagsource().equals("Crowdsourcing"))
		{
						//System.out.println("Source of flag is Crowdsourcing.");
			//get row id
			Crowdsourcing crowd = crowds.get(commentFlag.getSourcecommentrowindex());//gets the crowdsouring item for the row that the user flagged as an issue.
		
			CrowdsourcingFlag csFlag = new CrowdsourcingFlag(); //maybe change this???
			csFlag.setCrowdsourcing(crowd);

//			if (commentFlag.getFlagchoice()==null)
//			{
//				Flagchoice fc = new Flagchoice();
//				fc.setCommentchoices("1");
//				commentFlag.setFlagchoice(fc);
//			}
			
			csFlag.setFlagchoice(commentFlag.getFlagchoice());
			csFlag.setFlagcomment(commentFlag.getFlagcomment());
			csFlag.setFlagdate(date);
			csFlag.setFlagsource(commentFlag.getFlagsource());
			csFlag.setUser(complainingUser);
			csFlag.setAdminreviewed(false);

			crowdFlagRepository.save(csFlag);  //update repo
			
		}

		//Comments Flag Section  
		if(commentFlag.getFlagsource().equals("Comment"))
		{
			//System.out.println("Source of flag is Comment...need to put remainding stuff in 'if' statement now");
			Comment issuecomment = cs.get(commentFlag.getSourcecommentrowindex()); //gets the comment for the row that the user flagged as an issue.
			//System.out.println("comment id comment = "+ issuecomment);
//			Flagchoice fc;
//			if (commentFlag.getFlagchoice()==null)
//			{
//				fc = new Flagchoice();
//				fc.setCommentchoices("1");
//				commentFlag.setFlagchoice(fc);
//			
//			}
			commentFlag.setUser(complainingUser);
			commentFlag.setComment(issuecomment);
			commentFlag.setFlagdate(date);	
			commentFlag.setAdminreviewed(false);
			
			
			commentFlagRepository.save(commentFlag);  //save to repo
		//	fc = null;
		}

		List<Integer> thumbup = Collections.EMPTY_LIST;


		Users u;
		int usergamepoints = 0;

		//check if user is logged in.
		if (!username.equals("anonymousUser"))
		{ 
			//get user points
			u = userRepository.findByUsername(username);
			if (gameRepository.countByGamificationByUser(u.getId())>0)
				usergamepoints = gameRepository.findBySumGamePointsByUser(u.getId());
		}

		//get list of tag words for this object
		List<TagName> tn = tagnameRepository.findBychobject_id(id);

		TagName t = new TagName();
		Comment c = new Comment();	
		CommentThumb ct = new CommentThumb();
		Crowdsourcing crowdsourcing = new Crowdsourcing();

		commentFlag = new CommentFlag(); //reset the commentFlag object contents.
		List<Flagchoice> flagchoices = flagchoiceRepository.findAll();

		model.addAttribute("flagchoices",flagchoices);
		model.addAttribute("commentFlag", commentFlag);
		model.addAttribute("participations", part);
		model.addAttribute("crowdsourcings", crowds);		
		model.addAttribute("crowdsourcing", crowdsourcing);
		model.addAttribute("usergamepoints", usergamepoints);
		model.addAttribute("thumbup", thumbup);
		model.addAttribute("imageNo", countimages);
		model.addAttribute("object", object);
		model.addAttribute("image",image);
		model.addAttribute("comments", cs);
		model.addAttribute("commentThumb",ct);
		model.addAttribute("tagnames", tn);
		model.addAttribute("comment", c);
		model.addAttribute("tagname",t);

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


		ChObject object = chobject.get(id); //retrieves the chobject with id from repo.

		List<Participation> part = participations.get(object);
		Role role = new Role();
		Participant participant = new Participant();

		if (part != null)
		{
			for (int i = 0; i < part.size(); i++)
			{
				
				participant = part.get(i).getParticipant();
				
				//next, populate participant with all records in participant (only id was entered before this)
				participant = participants.get(participant.getPerson_id()); 
				role = part.get(i).getRole();
				role = roles.get(role.getRole_id());
				
				//add populated role and participant back to participation
				part.get(i).setParticipant(participant);
				part.get(i).setRole(role);
			}
		}

		List<Image> chimages = imageService.find(id); //retrieves the images for chobject id from repo
		Image image = new Image();
		int countimages = 0;
		if (chimages != null)
		{
			countimages = chimages.size();
			int i = 0;
			do
			{
				image = chimages.get(i);
				i++;
			} while (( i < chimages.size()) && (image.getUrl() != null));
		}


		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		date = new Date();  //get todays date

		GameType gt;
		Gamification g;

		Comment cthumb = commentRepository.findOne(commentid);
		System.out.println("comment id comment = "+ cthumb);
		
		//use cthumb to find user that entered comment so that points can be attributed too.
		Users uthumb = cthumb.getUser(); //unsure if this will work.
		System.out.println("User = "+uthumb+", if null, then retrieve user name using repository and search by username");

		List<Comment> cs = commentRepository.findBychobject_idANDFlagFalse(id);

		List<Integer> thumbup = Collections.EMPTY_LIST;

		Users u;
		int usergamepoints = 0;

		if (username !=null)
		{ 
			u = userRepository.findByUsername(username);
			//System.out.println("User details is: "+u);
			gt = new GameType();

			
			if (thumb.equals("thumbup"))
			{	
				gt = gameTypeRepository.findByGamename("Thumb Up");

				//Create new Gamification object 

				g = new Gamification();
				g.setChobject_id(id); //not really needed!!
				g.setDatestamp(date);
				g.setGamepoints(gt.getGamepoints());
				g.setUser(uthumb);
				g.setGametype(gt);
				System.out.println("Saving Gamification = "+g);
				gameRepository.save(g);
				

				Comment comm = commentRepository.findOne(commentid);
				
				comm.setThumbupcount(comm.getThumbupcount()+1); //increment Thumbup count
				commentRepository.save(comm);
				
				comm = commentRepository.findOne(commentid);

				CommentThumb commentThumb = new CommentThumb();
				commentThumb.setUser(u);
				commentThumb.setComment(comm);
				commentThumb.setGamification(g);
				commentThumb.setThumbup(true);
				commentThumb.setThumbdown(false);  //not sure this is necessary!!

				ctRepository.save(commentThumb);
			}

			if (thumb.equals("thumbdown"))
			{	

				gt = gameTypeRepository.findByGamename("Thumb down");

				g = new Gamification();
				g.setChobject_id(id); //not really needed!!
				g.setDatestamp(date);
				g.setGamepoints(gt.getGamepoints());
				g.setUser(uthumb);
				g.setGametype(gt);

				gameRepository.save(g);
			

				Comment comm = commentRepository.findOne(commentid);
				
				
				comm.setThumbdowncount(comm.getThumbdowncount()+1); //increment Thumbup count
				
				commentRepository.save(comm);
				comm = commentRepository.findOne(commentid);

				CommentThumb commentThumb = new CommentThumb();
				commentThumb.setUser(u);
				commentThumb.setComment(comm);
				commentThumb.setGamification(g);
				commentThumb.setThumbdown(true);
			

				ctRepository.save(commentThumb);
			}

			if (gameRepository.countByGamificationByUser(u.getId())>0)
				usergamepoints = gameRepository.findBySumGamePointsByUser(u.getId());

		}

		List<TagName> tn = tagnameRepository.findBychobject_id(id);


		TagName t = new TagName();
		Comment c = new Comment();	
		CommentThumb ct = new CommentThumb();
		Crowdsourcing crowdsourcing = new Crowdsourcing();

		List<Crowdsourcing> crowds = crowdsourcingRepository.findBychobject_idANDflagFalse(id); //add crowdsourced descriptions for this chObject.
		CommentFlag commentFlag = new CommentFlag();
		List<Flagchoice> flagchoices = flagchoiceRepository.findAll();

		model.addAttribute("flagchoices",flagchoices);
		model.addAttribute("commentFlag", commentFlag);
		model.addAttribute("participations", part);
		model.addAttribute("crowdsourcings", crowds);		
		model.addAttribute("crowdsourcing", crowdsourcing);
		model.addAttribute("usergamepoints", usergamepoints);
		model.addAttribute("thumbup", thumbup);
		model.addAttribute("imageNo", countimages);
		model.addAttribute("object", object);
		model.addAttribute("image",image);
		model.addAttribute("comments", cs);
		model.addAttribute("commentThumb",ct);
		model.addAttribute("tagnames", tn);
		model.addAttribute("comment", c);
		model.addAttribute("tagname",t);

		return "displayItem";			
		
	}  

} //end of class





