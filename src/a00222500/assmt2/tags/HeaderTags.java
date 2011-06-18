package a00222500.assmt2.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * HeaderTags is a custom tag designed to take in a Vector object
 * and parse through it displaying all of the header values
 * of a given query.
 * 
 * Background color of the table can be set through attribute.
 * Color must be CSS recognized color.
 * 
 * @author srobarts
 *
 */
public class HeaderTags extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Vector headerNames;
	private String backgroundColor;
	
	public void setBackgroundColor(String bgcolor)
	{
		//color must be a CSS recognized color (full word - not hex value)
		this.backgroundColor = bgcolor;
	}
	
	@SuppressWarnings("rawtypes")
	public void setHeaderNames(Object headerNames) {
	      this.headerNames = (Vector) headerNames;
	}
	
	public int doStartTag()
	{		
		try {
			JspWriter out = pageContext.getOut();
			out.print("<table style=\"background-color: " + backgroundColor + ";\">");
			//iterate through headers
			@SuppressWarnings("rawtypes")
			Iterator headers = headerNames.iterator();
			out.print("<tr>");
			while (headers.hasNext()) {
				String title = (String)headers.next();
				out.print("<th>" + title + "</th>");
			}
			//out.print("</tr>");  -- not going to close out here - close out in jsp
		} catch (IOException ioe) {
			System.out.println("Error in tag: " + ioe);
		}
		return(EVAL_BODY_INCLUDE);
	}
	

}
