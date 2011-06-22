package a00222500.assmt2.intl;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TranslateServlet
 */
@WebServlet("/TranslateServlet")
public class TranslateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranslateServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Locale locale = new Locale("en", "CA"); //initialize
		if(request.getParameter("lang") == null){
			//no lang - check session
			if(session.getAttribute("myLocale") != null){
				locale = (Locale) session.getAttribute("myLocale");
			}
		} else {
			String lang = request.getParameter("lang");
			if (lang.equals("German")) {
		       locale = new Locale("de", "DE");
		    } else if (lang.equals("English")) {
		         locale = new Locale("en", "CA");
		    } else {
		         locale = new Locale("en", "CA");
		    }
		}
	    session.setAttribute("myLocale",locale);
	    ResourceBundle bundle = ResourceBundle.getBundle("Message",locale);
	    
	    //page_title
	    String page_title = bundle.getString("page_title");
	    session.setAttribute("page_title", page_title);
	    
	    //header_text
	    String header_text = bundle.getString("header_text");
	    session.setAttribute("header_text", header_text);
	    
	    //welcome
	    String welcome = bundle.getString("welcome");
	    session.setAttribute("welcome", welcome);
	    
	    //intro1
	    String intro_sentence1 = bundle.getString("intro_sentence1");
	    session.setAttribute("intro_sentence1", intro_sentence1);
	    
	    //intro2
	    String intro_sentence2 = bundle.getString("intro_sentence2");
	    session.setAttribute("intro_sentence2", intro_sentence2);
	    
	    //supported
	    String supported_ops = bundle.getString("supported_ops");
	    session.setAttribute("supported_ops", supported_ops);
	    
	    //search
	    String search = bundle.getString("search");
	    session.setAttribute("search", search);
	    
	    //add
	    String add = bundle.getString("add");
	    session.setAttribute("add", add);
	    
	    //update
	    String update = bundle.getString("update");
	    session.setAttribute("update", update);
	    
	    //delete
	    String delete = bundle.getString("delete");
	    session.setAttribute("delete", delete);
	    
	    //start_page
	    String start_page = bundle.getString("start_page");
	    session.setAttribute("start_page", start_page);
	    
	    //about_page
	    String about_page = bundle.getString("about_page");
	    session.setAttribute("about_page", about_page);   
	    
	    //copyright
	    String copyright = bundle.getString("copyright");
	    session.setAttribute("copyright", copyright);
		
		//forward to about.jsp page
		String url = "/about.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
