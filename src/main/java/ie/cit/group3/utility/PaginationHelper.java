package ie.cit.group3.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;



/**
 * @author Tutorial from http://codefutures.com/spring-jdbc-pagination/
 * 
 * Modified by: john murphy to suit the purposes of this project
 * 
 * The method 'fetchpage' is effectively repeated for each object type that Pagination is required for (as each object type has its
 * own custom rowMapper).
 * 		method fetchpage() is used for ChObjects
 * 		method fetchpageRole() is used for Role objects
 * 		method fetchpageParticipant() is used for Participant objects 
 *
 * @param <E>
 */
public class PaginationHelper<E> {


	
/**
 * This method fetchpage() is used for ChObjects
 * @param jt (JdbcTemplate object)
 * @param sqlCountRows (String)
 * @param sqlFetchRows (String)
 * @param pageNo	(int)
 * @param pageSize	(int)
 * @param rowMapper	(RowMapper)
 * @return Page of ChObjects
 */
public Page<E> fetchpage(final JdbcTemplate jt, final String sqlCountRows, final String sqlFetchRows, 
			final int pageNo, final int pageSize, final ChObjectRowMapper rowMapper)

	{
		//determine how may rows are available
		final int rowCount = jt.queryForInt(sqlCountRows);

		//calculate the number of pages
		int pageCount = rowCount / pageSize;
		if (rowCount > pageSize * pageCount)
			pageCount++; //increment page count if partial page (final page) 
		
		//create the page object
		final Page<E> page = new Page<E>();
		page.setPageNumber(pageNo);
		page.setPagesAvailable(pageCount);

		//fetch a single page of results
		final int startRow = (pageNo - 1)* pageSize;
		
		jt.query(sqlFetchRows, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException
			{
				final List pageItems = page.getPageItems();
				int currentRow = 0;
				while (rs.next() && currentRow < startRow + pageSize)
				{
					if (currentRow >= startRow)
					{
						pageItems.add(rowMapper.mapRow(rs, currentRow));
					}
					currentRow++;
				}
				return page;
			}
		});
		return page;
	}


/**
 * method fetchpageRole() is used for Role objects
 * @param jt (JdbcTemplate object)
 * @param sqlCountRows (String)
 * @param sqlFetchRows (String)
 * @param pageNo	(int)
 * @param pageSize	(int)
 * @param rowMapper	(RowMapper)
 * @return Page of Role 
 */
public Page<E> fetchpageRole(final JdbcTemplate jt, final String sqlCountRows, final String sqlFetchRows, 
		final int pageNo, final int pageSize, final RolesRowMapper rowMapper)

{
	//determine how may rows are available
	final int rowCount = jt.queryForInt(sqlCountRows);
	//final int rowCount = jt.queryForInt(sqlCountRows, args);
	
	//calculate the number of pages
	int pageCount = rowCount / pageSize;
	if (rowCount > pageSize * pageCount)
		pageCount++; //increment page count if partial page (final page) 
	
	//create the page object
	final Page<E> page = new Page<E>();
	page.setPageNumber(pageNo);
	page.setPagesAvailable(pageCount);

	//fetch a single page of results
	final int startRow = (pageNo - 1)* pageSize;
	
	jt.query(sqlFetchRows, new ResultSetExtractor() {
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException
		{
			final List pageItems = page.getPageItems();
			int currentRow = 0;
			while (rs.next() && currentRow < startRow + pageSize)
			{
				if (currentRow >= startRow)
				{
					pageItems.add(rowMapper.mapRow(rs, currentRow));
				}
				currentRow++;
			}
			return page;
		}
	});
	return page;
}

/**
 *  method fetchpageParticipant() is used for Participant objects
 * @param jt (JdbcTemplate object)
 * @param sqlCountRows (String)
 * @param sqlFetchRows (String)
 * @param pageNo	(int)
 * @param pageSize	(int)
 * @param rowMapper	(RowMapper)
 * @return Page of Participant 
 */
public Page<E> fetchpageParticipant(final JdbcTemplate jt, final String sqlCountRows, final String sqlFetchRows, 
		final int pageNo, final int pageSize, final ParticipantRowMapper rowMapper)

{
	//determine how may rows are available
	final int rowCount = jt.queryForInt(sqlCountRows);
	//final int rowCount = jt.queryForInt(sqlCountRows, args);
	
	//calculate the number of pages
	int pageCount = rowCount / pageSize;
	if (rowCount > pageSize * pageCount)
		pageCount++; //increment page count if partial page (final page) 
	
	//create the page object
	final Page<E> page = new Page<E>();
	page.setPageNumber(pageNo);
	page.setPagesAvailable(pageCount);

	//fetch a single page of results
	final int startRow = (pageNo - 1)* pageSize;
	
	jt.query(sqlFetchRows, new ResultSetExtractor() {
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException
		{
			final List pageItems = page.getPageItems();
			int currentRow = 0;
			while (rs.next() && currentRow < startRow + pageSize)
			{
				if (currentRow >= startRow)
				{
					pageItems.add(rowMapper.mapRow(rs, currentRow));
				}
				currentRow++;
			}
			return page;
		}
	});
	return page;
}

}
