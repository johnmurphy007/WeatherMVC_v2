package ie.cit.group3.utility;

/**
 * @author john murphy
 * This class is used for 2 forms(html) as the form backing bean to take user input.
 * 1) Used for taking the 'Browse/Search' options that a user inputs into the 'displayOptions.html' which is located at: {base project URL}/browse/ 
 * 		This form captures the Type of search (on Museum Object or People or Roles or Crowdsourced Descriptions/UserComments/Tagwords), the sub-heading (
 * 		if one exists, e.g.: search by Title, search by ID etc), it captures the search criteria, the number of pages to be returned in the search results, 
 * 		the sort direction of the returned search.
 * 	2) Used for capturing the Directory to import JSON files from in import.html.	
 *
 */

public class GeneralFormBackingBean {

	private String string1;
	private String string2;
	private String string3;
	private String string4;
	private String string5;
	private String string6;
	private String string7;
	private String string8;
	private String string9;
	private int int1;
	private int int2;
	
	
	
	
	public GeneralFormBackingBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	



	@Override
	public String toString() {
		return "GeneralFormBackingBean [string1=" + string1 + ", string2="
				+ string2 + ", string3=" + string3 + ", string4=" + string4
				+ ", string5=" + string5 + ", string6=" + string6
				+ ", string7=" + string7 + ", string8=" + string8
				+ ", string9=" + string9 + ", int1=" + int1 + ", int2=" + int2
				+ "]";
	}







	public String getString1() {
		return string1;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public String getString2() {
		return string2;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public String getString3() {
		return string3;
	}
	public void setString3(String string3) {
		this.string3 = string3;
	}
	public int getInt1() {
		return int1;
	}
	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public int getInt2() {
		return int2;
	}
	public void setInt2(int int2) {
		this.int2 = int2;
	}



	public String getString4() {
		return string4;
	}



	public void setString4(String string4) {
		this.string4 = string4;
	}



	public String getString5() {
		return string5;
	}



	public void setString5(String string5) {
		this.string5 = string5;
	}



	public String getString6() {
		return string6;
	}



	public void setString6(String string6) {
		this.string6 = string6;
	}







	public String getString7() {
		return string7;
	}







	public void setString7(String string7) {
		this.string7 = string7;
	}







	public String getString8() {
		return string8;
	}







	public void setString8(String string8) {
		this.string8 = string8;
	}







	public String getString9() {
		return string9;
	}







	public void setString9(String string9) {
		this.string9 = string9;
	}
	
	
	
}
