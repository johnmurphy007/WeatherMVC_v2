package ie.cit.group3.repository;

import ie.cit.group3.domain.ChObject;
import ie.cit.group3.utility.ChObjectRowMapper;
import ie.cit.group3.utility.Page;
import ie.cit.group3.utility.PaginationHelper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author John Murphy
 * 
 * This class implements the interface for ChObjectRepository
 * Activities it must implement are:
 * 	get			Retrieve record(s) that match a given id
 *  save		Save a object/record to the repository
 *  remove		Delete an object/record from the repository
 *  findAll		list all the objects/records in the repository
 * 	Page<ChObject> findAll(int pageNo, int pageSize)	Returns a page of ChObjects
 * 	int CountAll()	 									Returns counts of all ChObjects
 *  int CountAllMedium()								Returns count of all 'Medium' attribute ChObjects * 
 */



//Identify this class as Repository (Spring will detect it during @ComponentScan).
@Repository
public class JdbcChObjectRepository implements ChObjectRepository 
{
	
	//Using JdbcTemplate to provide repository access using SQL statements
	private JdbcTemplate jdbcTemplate;
	
	//Using Constructor DI, auto-inject the DataBase dependency connection into this object.
	@Autowired
	public JdbcChObjectRepository(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	//Override the interface method get(String id)
	@Override
	public ChObject get(String id) 
	{
		/* This method retrieves an object (ChObject) based on a supplied String based 'id'.
		 * 
		 * Need to check that the record exists in the database first before returning it.
		 * Otherwise if the record does not exist, you will get an exception that the application
		 * cannot handle.
		 * Using a SQL COUNT statement to return the number of records.  This will return an integer
		 * 'count'.  If this is 0, then no match exists and the return type of the object is set to 
		 * null. If 'count' is >0, then run the SQL query to return the number of records that match
		 * the supplied String 'id'.
		 * 
		 * */
		
		//Other alternatives include SELECT TOP etc.  This query should be selected based whichever gives the best performance.
		String sql = "SELECT COUNT(id) FROM chObject WHERE id = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {id});
		
		if (count > 0)
		{
			sql = "SELECT * FROM chObject WHERE id = ?";
			ChObject chObject = jdbcTemplate.queryForObject(sql, new Object [] {id}, new ChObjectRowMapper());
			return chObject;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void save(ChObject chobject) 
	{
		//Implementing only the 'add()' part of 'save()' at present.
		String sql = "SELECT COUNT(id) FROM chObject WHERE id = ?";
		@SuppressWarnings("deprecation")
		int count = jdbcTemplate.queryForInt(sql, new Object [] {chobject.getId()});
		
		if (count > 0)
			update(chobject);
		else

			add(chobject);
	}

	private void add(ChObject chobject)
	{
		//Method to add a record to the repository
		String sql = "INSERT INTO chObject (creditline, date, description, gallery_text,id, medium, title) VALUES (?,?,?,?,?,?,?) ";

		jdbcTemplate.update(sql, new Object[] {chobject.getCreditline(),chobject.getDate(),chobject.getDescription(),chobject.getGallery_text()
				,chobject.getId(),chobject.getMedium(),chobject.getTitle()} );
	
	}
	
	private void update(ChObject chobject)
	{
		//NOT IMPLEMENTED/TESTED.
		//Method to update a record in the repository
		String sql = "UPDATE chObject SET creditline = ?, date =?, description=?, gallery_text =?, medium = ?, title=? WHERE id = ?";
		
		System.out.println("updating record");
		
		jdbcTemplate.update(sql, new Object[] {chobject.getCreditline(),chobject.getDate(),chobject.getDescription(),chobject.getGallery_text()
				,chobject.getMedium(),chobject.getTitle(),chobject.getId()} );
	}
	
	@Override
	public void remove(ChObject chobject) 
	{
		//Method to delete a record from the repository
		String sql = "DELETE FROM chObject WHERE id = ?";

		jdbcTemplate.update(sql, new Object[] {chobject.getId()});
	}

	@Override
	public List<ChObject> findAll() 
	{
		//Return a List of Object that contain all the info for Cultural Heritage objects
		String sql = "SELECT * FROM chObject";
		return jdbcTemplate.query(sql,  new ChObjectRowMapper());
	}
	
	@Override
	public Page<ChObject> findAll(int pageNo, int pageSize) 
	{
		PaginationHelper<ChObject> ph = new PaginationHelper<ChObject>();
		
		return ph.fetchpage(jdbcTemplate, 
				"SELECT COUNT(*) FROM chObject", 
				"SELECT * FROM chObject", 
				pageNo, pageSize, new ChObjectRowMapper());
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public int CountAll() 
	{
		//Return a Count of the number of CHObject in Cultural Heritage Museum
		//using integer as return value as range is from >-2Billion to >+2Billion
		String sql = "SELECT COUNT(*) FROM chObject";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public int CountAllMedium() {
		// TODO returns the number of different medium type in ChObject Repository
		String sql = "SELECT COUNT(medium) FROM chObject GROUP BY medium";
		return jdbcTemplate.queryForInt(sql);
	}

}
