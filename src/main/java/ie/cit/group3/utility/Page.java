package ie.cit.group3.utility;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Tutorial from http://codefutures.com/spring-jdbc-pagination/
 * 
 * This class (along with its companion class: PaginationHelper.java) is used to provide Page capability to Jdbc Repository objects.
 * This is beneficial as query results from Jdbc access objects can be returned page-by-page.
 *
 * @param <E>
 */
public class Page<E> {
	
	private int pageNumber;		//used to capture the current page number
	private int pagesAvailable;  //used to capture the number of pages available
	private List<E> pageItems = new ArrayList<E>();
	
	
	
	
	public Page() {
		super();
		// TODO Empty constructor
	}
	
	//Getters & Setters
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPagesAvailable() {
		return pagesAvailable;
	}
	public void setPagesAvailable(int pagesAvailable) {
		this.pagesAvailable = pagesAvailable;
	}
	public List<E> getPageItems() {
		return pageItems;
	}
	public void setPageItems(List<E> pageItems) {
		this.pageItems = pageItems;
	}

	
	
}
