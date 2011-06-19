package a00222500.assmt2.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class UpdateTags extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector rowData;
	private int count = 0;
	
	@SuppressWarnings("rawtypes")
	public void setRowData(Object rowData) {
	      this.rowData = (Vector) rowData;
	}
	
	public int doStartTag()
	{		
		try {
			JspWriter out = pageContext.getOut();
			//iterate through headers
			@SuppressWarnings("rawtypes")
			Iterator rows = rowData.iterator();
			
			String field;
			int fieldCount = 1;
			String memberID = "";
			String firstName = "";
			String lastName = "";
			String address = "";
			String city = "";
			String country = "";
			String code = "";
			String phoneNumber = "";
			String email = "";
			
			while (rows.hasNext()) {
				@SuppressWarnings("rawtypes")
				Vector singleRow = (Vector)rows.next();
				@SuppressWarnings("rawtypes")
				Iterator fields = singleRow.iterator();
				while (fields.hasNext()) {
					field = (String)fields.next();
					if(fieldCount == 1){ 
						//if we are looking at the first field it is the memberID
						memberID = field; 
					} else if(fieldCount == 2) {
						firstName = field;
					} else if(fieldCount == 3) {
						lastName = field;
					} else if(fieldCount == 4) {
						address = field;
					} else if(fieldCount == 5) {
						city = field;
					} else if(fieldCount == 6) {
						country = field;
					} else if(fieldCount == 7) {
						code = field;
					} else if(fieldCount == 8) {
						phoneNumber = field;
					} else if(fieldCount == 9) {
						email = field;
					}
					fieldCount++;
				}
				//reset field count
				fieldCount = 1;
				count++;
				
				out.print("<label>MemberID:</label><input type=\"text\" name=\"memberID\" value=\"" + memberID + "\" />");
				out.print("<label>First Name:</label><input type=\"text\" name=\"firstName\" value=\"" + firstName + "\" />");
				out.print("<label>Last Name:</label><input type=\"text\" name=\"lastName\" value=\"" + lastName + "\" />");
				out.print("<label>Address:</label><input type=\"text\" name=\"address\" value=\"" + address + "\" />");
				out.print("<label>City:</label><input type=\"text\" name=\"city\" value=\"" + city + "\" />");
				out.print("<label>Country:</label><input type=\"text\" name=\"country\" value=\"" + country + "\" />");
				out.print("<label>Code:</label><input type=\"text\" name=\"code\" value=\"" + code + "\" />");
				out.print("<label>Phone Number:</label><input type=\"text\" name=\"phoneNumber\" value=\"" + phoneNumber + "\" />");
				out.print("<label>Email:</label><input type=\"text\" name=\"email\" value=\"" + email + "\" />");
			}
			out.print("</table>");
		} catch (IOException ioe) {
			System.out.println("Error in tag: " + ioe);
		}
		return(EVAL_BODY_INCLUDE);
	}

}
