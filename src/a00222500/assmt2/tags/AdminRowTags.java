package a00222500.assmt2.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * RowTags is a custom tag created to display the row data from a SQL database query.
 * The data is handed to the code as a Vector object.  The object is then parsed
 * through to display the table data.
 * 
 * Programmer can set the color of the rows using the rowColors attribute.  There 
 * are two choices for the row colors - tan and red.  Tan will show alternating colors of tan
 * as the backround for the rows.  Red will show a red an yellow pattern for the background
 * color of the rows.  The display will default to a green and white pattern if no selection
 * is given.
 * 
 * @author srobarts
 *
 */

public class AdminRowTags extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector rowData;
	private int count = 0;
	private String evenRowColor = "";
	private String oddRowColor = "";
	private String memberID = "";
	
	@SuppressWarnings("rawtypes")
	public void setRowData(Object rowData) {
	      this.rowData = (Vector) rowData;
	}
	
	public void setEvenRowColor(String clr)
	{
		this.evenRowColor = clr;
	}
	
	public void setOddRowColor(String clr)
	{
		this.oddRowColor = clr;
	}
	
	public int doStartTag()
	{		
		try {
			JspWriter out = pageContext.getOut();
			//iterate through headers
			@SuppressWarnings("rawtypes")
			Iterator rows = rowData.iterator();
			
			while (rows.hasNext()) {
				int fieldCount = 1;
				if (count % 2 == 0) {
					out.print("<tr bgcolor=\"" + evenRowColor + "\">");
				} else {
					out.print("<tr bgcolor=\"" + oddRowColor + "\">");
				}
				@SuppressWarnings("rawtypes")
				Vector singleRow = (Vector)rows.next();
				@SuppressWarnings("rawtypes")
				Iterator fields = singleRow.iterator();
				while (fields.hasNext()) {
					String field = (String)fields.next();
					if(fieldCount == 1){ 
						//if we are looking at the first field it is the memberID
						memberID = field; 
					}
					out.print("<td>" + field + "</td>");
					fieldCount++;
				}
				//reset field count
				out.print("<form id=\"two\" name=\"form\" method=\"post\" action=\"query_database\">");
				out.print("<td><input type=\"radio\" name=\"action\" value=\"modify\" checked=\"checked\" />Update</td>");
				out.print("<td><input type=\"radio\" name=\"action\" value=\"delete\" />Delete</td>");
				out.print("<input type=\"hidden\" name=\"memberID\" value=\"" + memberID + "\" />");
				out.print("<td><button type=\"submit\">Submit</button></td>");
				out.print("</form>");
				out.print("</tr>");
				count++;
			}
			out.print("</table>");
		} catch (IOException ioe) {
			System.out.println("Error in tag: " + ioe);
		}
		return(EVAL_BODY_INCLUDE);
	}

}

