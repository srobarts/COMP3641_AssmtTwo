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

public class RowTags extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector rowData;
	private int count = 0;
	private int fieldCount = 0;
	private String evenRowColor = "";
	private String oddRowColor = "";
	
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
					out.print("<td>" + field + "</td>");
					fieldCount++;
				}
				count++;
			}
			out.print("</table>");
		} catch (IOException ioe) {
			System.out.println("Error in tag: " + ioe);
		}
		return(EVAL_BODY_INCLUDE);
	}

}

