package ie.cit.group3.controller;


import java.util.Date;
import java.util.List;

import ie.cit.group3.domain.ChObject;
import ie.cit.group3.entity.Comment;
import ie.cit.group3.entity.CommentFlag;
import ie.cit.group3.entity.Crowdsourcing;
import ie.cit.group3.entity.Flagchoice;
import ie.cit.group3.entity.TagName;
import ie.cit.group3.entity.Users;
import ie.cit.group3.domain.Image;
import ie.cit.group3.domain.Participant;
import ie.cit.group3.domain.Participation;
import ie.cit.group3.domain.Role;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author John Murphy
 * 
 * This class handles the URL "/browse" requests.
 * 
 * This class interfaces with the dispatchServlet to handles its URL requests and serves it the data (ModelMap) and html file to use to 
 * display the data.
 */


@Controller
//@RestController
@RequestMapping("/browse")
public class BrowseController {

	/*
	 * Note: Started with Repository access objects.  Recently upgraded these to service access objects, but leaving original Repository access
	 * objects in-situ (just commented them out) as it is close to assignment handup and weary about making too many last minute adjustments
	 *
	 */

	@Autowired
	ImageService imageService;			//Provides access to repository layer through service layer

	//	@Autowired
	//	CommentRepository commentRepository;		
	@Autowired
	CommentService commentRepository;		//Provides access to repository layer through service layer


	//	@Autowired
	//	CommentThumbRepository ctRepository;
	@Autowired
	CommentThumbService ctRepository;		//Provides access to repository layer through service layer

	@Autowired
	RolesService roles;					//Provides access to repository layer through service layer

	@Autowired
	ParticipantService participants;	//Provides access to repository layer through service layer

	@Autowired
	ParticipationService participations; //Provides access to repository layer through service layer

	//	@Autowired
	//	ImageRepository images;  //changed to Repository (from Service 5/5/2015...change back once debugged)

	//	@Autowired
	//	CrowdsourcingRepository crowdsourcingRepository;
	@Autowired
	CrowdsourcingService crowdsourcingRepository;	//Provides access to repository layer through service layer

	//	@Autowired
	//	UserRepository userRepository;
	@Autowired
	UserService userRepository;				//Provides access to repository layer through service layer

	//	@Autowired
	//	GamificationRepository gameRepository;
	@Autowired
	GamificationService gameRepository;		//Provides access to repository layer through service layer

	//	@Autowired
	//	GameTypeRepository gameTypeRepository;
	@Autowired
	GameTypeService gameTypeRepository;		//Provides access to repository layer through service layer

	//	@Autowired
	//	TagNameRepository tagnameRepository;
	@Autowired
	TagNameService tagnameRepository;		//Provides access to repository layer through service layer

	//	@Autowired
	//	CommentFlagRepository commentFlagRepository;
	@Autowired
	CommentFlagService commentFlagRepository;	//Provides access to repository layer through service layer

	//	@Autowired
	//	UsersRepository usersRepoPassword;
	@Autowired
	UsersService usersRepoPassword;		//Provides access to repository layer through service layer

	//	@Autowired
	//	AuthoritiesRepository authoritiesRepository;
	@Autowired
	AuthoritiesService authoritiesRepository;	//Provides access to repository layer through service layer

	//	@Autowired
	//	FlagchoiceRepository flagchoiceRepository;
	@Autowired
	FlagchoiceService flagchoiceRepository;		//Provides access to repository layer through service layer

	//	@Autowired
	//	CrowdsourceFlagRepository crowdFlagRepository;
	@Autowired
	CrowdsourceFlagService crowdFlagRepository;	//Provides access to repository layer through service layer

	@DateTimeFormat (pattern="dd-MM-YYYY")
	Date date ;	
	//	@Autowired
	//	ChObjectRepository chobject; //changed to Repository (from Service 5/5/2015...change back once debugged)
	@Autowired
	ChObjectService chobject; 			//Provides access to repository layer through service layer

	//	@Autowired
	//	JPAChObjectRepository jpaChobject;
	@Autowired
	JPAChObjectService jpaChobject;		//Provides access to repository layer through service layer


	/**
	 * BrowseOptions: This method captures the /browse/ URL.  It uses the form backing bean: "GeneralFormBackingBean".
	 * It supplies the following data to the html form: blank form backing bean, blank message (this will be populated if no search info is found), 
	 * count of the number of objects in the database, count of the number of participants in the database and count of the number of roles in the 
	 * database
	 * RequestMethod.GET
	 * @param ModelMap model
	 * @return displayOptions.html as view
	 */
	@RequestMapping(value="/", method = RequestMethod.GET) 
	public String BrowseOptions(ModelMap model) {			

		GeneralFormBackingBean searchoptions = new GeneralFormBackingBean();
		String message="";

		model.addAttribute("searchoptions",searchoptions);
		model.addAttribute("message",message);
		model.addAttribute("countobjects", chobject.CountAll());
		model.addAttribute("countpeople", participants.CountAll() );
		model.addAttribute("countrole", roles.CountRole() );
		//	model.addAttribute("countmedium",jpachobject.countDistinctMedium());
		return "displayOptions";			
	}  


	/**
	 * listAll: This method used for /browse/listall/objects
	 * It generates a list of ChObject items.
	 * RequestMethod.GET
	 * @param model
	 * @return displayObjects.html as view
	 */
	@RequestMapping(value="/listall/objects", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			

		List<ChObject> listobjects =chobject.findAll();
		model.addAttribute("objects", listobjects);
		return "displayObjects";			
	}  

	/**
	 * listAllPage: This method used for /browse/listall/objects/{pageNo}/{pageSize}
	 * It generates a page of ChObject items based on page number and page size.
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize
	 * @return displayObjects.html as view
	 */
	@RequestMapping(value="/listall/objects/{pageNo}/{pageSize}", method = RequestMethod.GET) 
	public String listAllPage(@PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{			

		ie.cit.group3.utility.Page<ChObject> listobjects =chobject.findAll(pageNo,pageSize);
		List<ChObject> listobject = listobjects.getPageItems();

		int count = chobject.CountAll();  //get total number of chobjects in repo. 

		int countpages = 0;

		//Get the number of pages (based on pageSize needed)
		//Another option is to use listobject.getPagesAvailable(). Can try this after project submission.
		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1; //add 1 for a partial page.

		model.addAttribute("countpages",countpages); //count of number of pages in total 
		model.addAttribute("pageNo", pageNo);		//page number to view
		model.addAttribute("pageSize", pageSize);	//number of objects to be shown per page
		model.addAttribute("objects", listobject);	//items in page to show.
		return "displayObjects";			
	}  


	/**
	 * listRole: This method used for /browse/listall/roles/{pageNo}/{pageSize}
	 * It generates a page of Role items based on page number and page size.
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize
	 * @return displayRoles.html as view
	 */
	@RequestMapping(value="/listall/roles/{pageNo}/{pageSize}", method = RequestMethod.GET) 
	public String listRole(@PathVariable int pageNo, @PathVariable int pageSize,ModelMap model) {			

		ie.cit.group3.utility.Page<Role> listroles =roles.findAll(pageNo,pageSize);
		List<Role> listrolesByPage = listroles.getPageItems(); 

		int count = roles.CountAll();		//get total number of chobjects in repo. 

		//Next get the number of pages (based on pageSize needed)
		//Another option is to use listobject.getPagesAvailable(). Can try this after project submission.
		int countpages = 0;

		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);//count of number of pages in total 
		model.addAttribute("pageNo", pageNo);		//page number to view
		model.addAttribute("pageSize", pageSize); 	//number of objects to be shown per page
		model.addAttribute("roles", listrolesByPage); //items in page to show.
		return "displayRoles";			
	}    

	/**
	 * listParticipant: This method used for /browse/listall/participants/{pageNo}/{pageSize}
	 * It generates a page of Role items based on page number and page size.
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize
	 * @return displayParticipants.html as view
	 */
	@RequestMapping(value="/listall/participants/{pageNo}/{pageSize}", method = RequestMethod.GET) 
	public String listParticipant(@PathVariable int pageNo, @PathVariable int pageSize,ModelMap model) {			

		ie.cit.group3.utility.Page<Participant> listparticipants = participants.findAll(pageNo,pageSize);
		List<Participant> listparticipantsByPage = listparticipants.getPageItems();

		int count = participants.CountAll();
		int countpages = 0;

		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("participants", listparticipantsByPage);
		return "displayParticipants";			
	}    

	/**
	 * BrowseSearchRequest: This method used to handle POST requests for /browse/ after the user has made their 'browse' selection criteria.
	 * RequestMethod.POST
	 * @param model, GeneratlFormBackingBean searchoptions
	 * @return <depends on the user selected option>
	 */
	@RequestMapping(value="/", method = RequestMethod.POST) 
	public String BrowseSearchRequest(@ModelAttribute("searchoptions") GeneralFormBackingBean searchoptions, ModelMap model) {			

		//Get user selected search/browse options
		int numpage = searchoptions.getInt1();        	//Captures the number of items per page to be displayed.
		String searchattribute = searchoptions.getString2(); 	//Captures a menu selection made by the User (e.g. Search by ID, Search by Medium etc)
		String searchcriteria = searchoptions.getString3();		//Captures the User entered search criteria text
		String sourceOfSearch = searchoptions.getString6();		//Captures the source of the search (e.g. Crowdsouring or Museum objects or People or Role)
		//This field is updated using JQuery
		String sortdirection = searchoptions.getString1(); 		//Captures if sort is ascending or descending.



		long count = 0; //used to count the number of items in the search result 
		long countpages= 0; //used to capture the number of pages of search results that can be displayed
		String searchtype = ""; //this will be used to populate the URL (so that the same user entered search criteria text can be passed from page to page).


		if (sourceOfSearch.equals("chobject") || sourceOfSearch.equals(""))
		{
			switch (searchattribute)
			{
			case "id":

				ChObject object = chobject.get(searchcriteria);//retrieves the chobject with id from repo.
				if (object != null)
				{
					//Retrieving the data required to populate all the fields when displaying the info for a Cultural Heritage Object.
					//The code below mirrors the code in HomeController for method that handles URL: "/object/{id}"

					//Used Jdbc for ChObject so the following code is manually adding participation data to the object.
					List<Participation> part = participations.get(object);  //get participations for this object
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
					List<Image> chimages = imageService.find(searchcriteria); //retrieves the images for chobject id from repo
					Image image = new Image();
					int countimages = 0;
					if (chimages != null)
					{
						countimages = chimages.size();
						int i = 0;
						do
						{
							image = chimages.get(i); //Retrieves the first image for this object.
							i++;
						} while (( i < chimages.size()) && (image.getUrl() != null));
					}

					//retrieve the comments for this object
					List<Comment> cs = commentRepository.findBychobject_idANDFlagFalse(searchcriteria);

					//retrieve the tag words for this object
					List<TagName> tn = tagnameRepository.findBychobject_id(searchcriteria);

					//retrieve the crowdsourced descriptions for this object
					List<Crowdsourcing> crowds = crowdsourcingRepository.findBychobject_idANDflagFalse(searchcriteria); //add crowdsourced descriptions for this chObject.




					//Get logged in user details
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					String username = authentication.getName();

					Users u;
					int usergamepoints = 0;

					if (!username.equals("anonymousUser"))   //i.e. User is logged in.
					{ 
						u = userRepository.findByUsername(username);
						if (gameRepository.countByGamificationByUser(u.getId())>0)
							usergamepoints = gameRepository.findBySumGamePointsByUser(u.getId());	 //get user game points
					}

					//Generate blank form back beans:
					TagName t = new TagName();
					Comment c = new Comment();	
					Crowdsourcing crowdsourcing = new Crowdsourcing();
					CommentFlag commentFlag = new CommentFlag();

					List<Flagchoice> flagchoices = flagchoiceRepository.findAll();

					//Supply the above generated data and form backing beans to html page.
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

					return "displayItem";			//display object
				}
				else
				{
					//display message saying results not found, please try again....
					searchoptions = new GeneralFormBackingBean();
					String message="Ooops...Could not find any object with ID = "+ searchcriteria+". Please try again.";

					model.addAttribute("searchoptions",searchoptions);

					model.addAttribute("message",message);
					model.addAttribute("countobjects", chobject.CountAll());
					model.addAttribute("countpeople", participants.CountAll() );
					model.addAttribute("countrole", roles.CountRole() );

					return "displayOptions";	 //re-display the search options (with message)
				}
				//break;

			case "title":
				//User selected 'Search by Title'.  This can return a list of objects that have a title that match the search criteria.

				Page<ChObject> listobjects=null;

				if (sortdirection.equals("ASC"))
				{
					listobjects = jpaChobject.findByTitleLikeOrderByTitleAsc(searchcriteria, new PageRequest(0, numpage));
				}
				else
				{
					listobjects = jpaChobject.findByTitleLikeOrderByTitleDesc(searchcriteria, new PageRequest(0, numpage));
				}	


				int pageNo = 1;

				count = jpaChobject.countByTitleLike(searchcriteria); //assigns count the number of records 
				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;

				searchtype = "title"; //this will be used to populate the URL
				//The same html form is used to populate the results for searching by Title, Medium, Description etc.
				//the only way to differential is using local variable 'searchtype'.  The value for 'searchtype' is passed
				//to the html page and the html page will insert this into the URL (Could do this using a Thymeleaf switch statement too).

				//Supply the above generated data and form backing beans to html page.
				model.addAttribute("searchtype",searchtype);
				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", listobjects);
				return "displayUserSearchPageObjects";
				//	break;

			case "medium":

				//User selected 'Search by Medium'.  This can return a list of objects that have a medium that match the search criteria.

				Page<ChObject> listobjects1=null;

				if (sortdirection.equals("ASC"))
				{
					listobjects1 = jpaChobject.findByMediumLikeOrderByMediumAsc(searchcriteria, new PageRequest(0, numpage));
				}
				else
				{
					listobjects1 = jpaChobject.findByMediumLikeOrderByMediumDesc(searchcriteria, new PageRequest(0, numpage));					
				}

				int pageNo1 = 1;

				count = jpaChobject.countByMediumLike(searchcriteria); //assigns count the number of records 
				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;

				searchtype = "medium"; //this will be used to populate the URL (see explanation in case: "title" above).

				//Supply the above generated data and form backing beans to html page.
				model.addAttribute("searchtype",searchtype);
				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo1);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", listobjects1);
				return "displayUserSearchPageObjects";

				//	break;

			case "creditline":

				//User selected 'Search by credit line'.  This can return a list of objects that have a creditline that match the search criteria.
				Page<ChObject> listobjects2 = null;
				if (sortdirection.equals("ASC"))
				{
					listobjects2 = jpaChobject.findByCreditlineLikeOrderByCreditlineAsc(searchcriteria, new PageRequest(0, numpage));
				}
				else
				{
					listobjects2 = jpaChobject.findByCreditlineLikeOrderByCreditlineDesc(searchcriteria, new PageRequest(0, numpage));

				}

				int pageNo2 = 1;

				count = jpaChobject.countByCreditlineLike(searchcriteria); //assigns count the number of records 
				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;

				searchtype = "creditline"; //this will be used to populate the URL.

				//Supply the above generated data and form backing beans to html page.
				model.addAttribute("searchtype",searchtype);
				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo2);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", listobjects2);
				return "displayUserSearchPageObjects";

				//break;

			case "description":

				Page<ChObject> listobjects3 =null;

				if (sortdirection.equals("ASC"))
				{
					listobjects3 = jpaChobject.findByDescriptionLikeOrderByDescriptionAsc(searchcriteria, new PageRequest(0, numpage));
				}
				else
				{
					listobjects3 = jpaChobject.findByDescriptionLikeOrderByDescriptionDesc(searchcriteria, new PageRequest(0, numpage));					
				}


				int pageNo3 = 1;

				count = jpaChobject.countByDescriptionLike(searchcriteria); //assigns count the number of records 
				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;

				searchtype = "description"; //this will be used to populate the URL.

				//Supply the above generated data and form backing beans to html page.
				model.addAttribute("searchtype",searchtype);
				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo3);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", listobjects3);
				return "displayUserSearchPageObjects";

				//	break;

			case "images":
				List<Image> listimages = imageService.findByImageId(searchcriteria);

				int pageNo4 = 1;

				count = imageService.countByImageId(searchcriteria); //assigns count the number of records 
				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;


				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo4);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("images", listimages);
				return "displayImages";

				//	break;

			}
		}

		if (sourceOfSearch.equals("participant"))
		{	
			String searchpeople = searchoptions.getString4();  //get name of person user entered as search criteria.
			List<Participant> listpeople = participants.findByPersonId(searchpeople);
			int pageNo5 = 1;

			count = participants.countByParticipant(searchcriteria); //assigns count the number of records 
			if ((count % numpage) == 0)
				countpages = count/numpage;
			else
				countpages = count/numpage + 1;

			model.addAttribute("countpages",countpages);
			model.addAttribute("searchcriteria",searchcriteria);
			model.addAttribute("pageNo", pageNo5);
			model.addAttribute("pageSize", numpage);
			model.addAttribute("images", listpeople);

			return "displayParticipants";
		}


		//Check if Roles
		if (sourceOfSearch.equals("role"))
		{
			String searchrole = searchoptions.getString5();  //get name of role user entered as search criteria.
			List<Role> listroles = roles.findByRoleId(searchrole);

			int pageNo6 = 1;

			count = roles.countByRoleId(searchcriteria); //assigns count the number of records 
			if ((count % numpage) == 0)
				countpages = count/numpage;
			else
				countpages = count/numpage + 1;

			model.addAttribute("countpages",countpages);
			model.addAttribute("searchcriteria",searchcriteria);
			model.addAttribute("pageNo", pageNo6);
			model.addAttribute("pageSize", numpage);
			model.addAttribute("images", listroles);

			return "displayRoles";
		}

		if (sourceOfSearch.equals("crowdsource-gamification"))
		{

			searchattribute = searchoptions.getString7();  //get the menu option user selected (e.g. search on Crowdsourcing, or Tagwords or Comments)
			searchcriteria = searchoptions.getString8();   //get search criteria the user entered.

			switch (searchattribute)
			{
			case "crowdsourcing":

				Page<Crowdsourcing> crowdsourcedobjects = crowdsourcingRepository.findByDescriptionLike(searchcriteria, new PageRequest(0, numpage));

				int pageNo = 1;

				count = crowdsourcingRepository.countByDescriptionLike(searchcriteria); //assigns count the number of records 

				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;


				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", crowdsourcedobjects);

				return "displayCrowdsourcingObjects";

			case "comments":

				Page<Comment> commentobjects = commentRepository.findByCommenttextLike(searchcriteria, new PageRequest(0, numpage));
				int pageNo2 = 1;

				count = commentRepository.countByCommenttextLike(searchcriteria); //assigns count the number of records 
				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;

				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo2);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", commentobjects);

				return "displayCommentObjects";



			case "tagwords":

				Page<TagName> tagnameobjects = tagnameRepository.findByTagwordLike(searchcriteria, new PageRequest(0, numpage));
				int pageNo3 = 1;

				count = tagnameRepository.countByTagwordLike(searchcriteria); //assigns count the number of records 

				if ((count % numpage) == 0)
					countpages = count/numpage;
				else
					countpages = count/numpage + 1;

				model.addAttribute("countpages",countpages);
				model.addAttribute("searchcriteria",searchcriteria);
				model.addAttribute("pageNo", pageNo3);
				model.addAttribute("pageSize", numpage);
				model.addAttribute("objects", tagnameobjects);

				return "displayTagWordsObjects";

			}


		}

		String message="Ooops...Could not find what you were looking for, please try again.";

		model.addAttribute("message",message);
		searchoptions = new GeneralFormBackingBean();
		model.addAttribute("searchoptions",searchoptions);

		model.addAttribute("countobjects", chobject.CountAll());
		model.addAttribute("countpeople", participants.CountAll() );
		model.addAttribute("countrole", roles.CountRole() );

		return "displayOptions";			
	}  

	/**
	 * listCrowdsourcingPage: This method used for /browse/crowdsourcing/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates a Page of Crowdsourcing items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayCrowdsourcingObjects.html as view
	 */
	@RequestMapping(value="/crowdsourcing/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listCrowdsourcingPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{

		Page<Crowdsourcing> crowdsourcedobjects = crowdsourcingRepository.findByDescriptionLike(searchcriteria, new PageRequest(pageNo-1, pageSize));
		
		long countpages = 0;
		long count = crowdsourcingRepository.countByDescriptionLike(searchcriteria); //assigns count the number of records 

		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);
		model.addAttribute("searchcriteria",searchcriteria);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", crowdsourcedobjects);

		return "displayCrowdsourcingObjects";
	}

	/**
	 * listCommentPage: This method used for /browse/comment/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates handles the GET request. Generates a Page of Comment items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayCommentObjects.html as view
	 */
	@RequestMapping(value="/comment/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listCommentPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{
		Page<Comment> commentobjects = commentRepository.findByCommenttextLike(searchcriteria, new PageRequest(pageNo-1, pageSize));

		long countpages =0;

		long count = commentRepository.countByCommenttextLike(searchcriteria); //assigns count the number of records 
		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);
		model.addAttribute("searchcriteria",searchcriteria);		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", commentobjects);
		
		return "displayCommentObjects";

	}

	/**
	 * listTagWordPage: This method used for /browse/tagword/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates handles the GET request. Generates a Page of TagName items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayTagWordsObjects.html as view
	 */
	@RequestMapping(value="/tagword/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listTagWordPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{

		Page<TagName> tagnameobjects = tagnameRepository.findByTagwordLike(searchcriteria, new PageRequest(pageNo-1, pageSize));

		long countpages =0;
		long count = tagnameRepository.countByTagwordLike(searchcriteria); //assigns count the number of records 

		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);
		model.addAttribute("searchcriteria",searchcriteria);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", tagnameobjects);
		
		return "displayTagWordsObjects";

	}

	/**
	 * listTitleItemsPage: This method used for /browse/title/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates handles the GET request. Generates a Page of ChObject items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayUserSearchPageObjects.html as view
	 */
	@RequestMapping(value="/title/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listTitleItemsPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{
		Page<ChObject> listobjects = jpaChobject.findByTitleLike(searchcriteria, new PageRequest(pageNo-1, pageSize));
		String searchtype = "title"; //this will be used to populate the URL.

		long countpages = 0;
		//			
		long count = jpaChobject.countByTitleLike(searchcriteria); //assigns count the number of records 
		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);
		model.addAttribute("searchtype",searchtype);
		model.addAttribute("searchcriteria",searchcriteria);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", listobjects);
		
		return "displayUserSearchPageObjects";
	}

	/**
	 * listMediumItemsPage: This method used for /browse/medium/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates handles the GET request. Generates a Page of ChObject items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayUserSearchPageObjects.html as view
	 */
	@RequestMapping(value="/medium/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listMediumItemsPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{

		Page<ChObject> listobjects1 = jpaChobject.findByMediumLike(searchcriteria, new PageRequest(pageNo-1, pageSize));
		String searchtype = "medium"; //this will be used to populate the URL.
		long countpages = 0;

		long count = jpaChobject.countByMediumLike(searchcriteria); //assigns count the number of records 
		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;

		model.addAttribute("countpages",countpages);
		model.addAttribute("searchtype",searchtype);
		model.addAttribute("searchcriteria",searchcriteria);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", listobjects1);
	
		return "displayUserSearchPageObjects";
	}

	/**
	 * listCreditLineItemsPage: This method used for /browse/creditline/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates handles the GET request. Generates a Page of ChObject items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayUserSearchPageObjects.html as view
	 */
	@RequestMapping(value="/creditline/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listCreditLineItemsPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{

		Page<ChObject> listobjects2 = jpaChobject.findByCreditlineLike(searchcriteria, new PageRequest(pageNo-1, pageSize));

		long countpages = 0;

		long count = jpaChobject.countByCreditlineLike(searchcriteria); //assigns count the number of records 
		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;
	
		String searchtype = "creditline"; //this will be used to populate the URL.
		
		model.addAttribute("searchtype",searchtype);
		model.addAttribute("countpages",countpages);
		model.addAttribute("searchcriteria",searchcriteria);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", listobjects2);
	
		return "displayUserSearchPageObjects";
	}

	/**
	 * listDescriptionItemsPage: This method used for /browse/description/{pageNo}/{pageSize}/{searchcriteria}
	 * It generates handles the GET request. Generates a Page of ChObject items that match the search criteria
	 * RequestMethod.GET
	 * @param model, int pageNo, int pageSize, String searchcriteria
	 * @return displayUserSearchPageObjects.html as view
	 */
	@RequestMapping(value="/description/{pageNo}/{pageSize}/{searchcriteria}", method = RequestMethod.GET) 
	public String listDescriptionItemsPage(@PathVariable String searchcriteria, @PathVariable int pageNo, @PathVariable int pageSize, ModelMap model) 
	{
		Page<ChObject> listobjects3 = jpaChobject.findByDescriptionLike(searchcriteria, new PageRequest(pageNo-1, pageSize));

		long countpages=0;

		long count = jpaChobject.countByDescriptionLike(searchcriteria); //assigns count the number of records 
		if ((count % pageSize) == 0)
			countpages = count/pageSize;
		else
			countpages = count/pageSize + 1;
	
		String searchtype = "description"; //this will be used to populate the URL.

		model.addAttribute("searchtype",searchtype);
		model.addAttribute("countpages",countpages);
		model.addAttribute("searchcriteria",searchcriteria);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("objects", listobjects3);
		
		return "displayUserSearchPageObjects";

	}

}             