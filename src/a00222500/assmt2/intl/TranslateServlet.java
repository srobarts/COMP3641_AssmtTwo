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
		String lang = request.getParameter("lang");
		Locale locale = new Locale("de", "DE");
		HttpSession session = request.getSession();
		
		if (lang == null) {
		   //set to default of Canadian english
		   locale=Locale.CANADA;
		} else {
			if (lang.equals("German")) {
		       locale = new Locale("de", "DE");
		    } else if (lang.equals("English")) {
		         locale = new Locale("en", "CA");
		    } else {
		         locale = new Locale("en", "CA");
		    }
		    session.setAttribute("myLocale",locale);
		    ResourceBundle bundle = ResourceBundle.getBundle("Message",locale);
		    String page_title = bundle.getString("page_title");
		    session.setAttribute("page_title", page_title);
		    
		    
		    
		    
		}
		
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
