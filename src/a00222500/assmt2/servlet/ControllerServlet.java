package a00222500.assmt2.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import a00222500.assmt2.util.*;
import a00222500.assmt2.data.*;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseBean db;
	private final String REG_PHONE = "(\\()?(\\d{3})(\\))?([\\.\\-\\/ ])?(\\d{3})([\\.\\-\\/ ])?(\\d{4})";
	private final String REG_EMAIL = "(\\w)([\\.-]?\\w+)*@(\\w+)([\\.-]?\\w+)*(\\.\\w{2,3})";
	private final String REG_STRING = "^[a-zA-Z' ]{1,40}$";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }
    
    /**
	 * servletInit() retrieves database information from web.xml and connects to database
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String url;
		String driver;
		String username;
		String password;
		
		db = new DatabaseBean();
		
		config = getServletConfig();
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url");
		username = config.getInitParameter("username");
		password = config.getInitParameter("password");
		
		//connect to database
		db.connect(url, username, password, driver);
		
		//create table if not exists
		String create_table_query = db.create_table_query();
		db.create_table(create_table_query);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initialize session variables
		HttpSession session = request.getSession();
		session.setAttribute("requestType", "query");
		session.setAttribute("insertStatus", "valid");
		//String sqlResult = "";	
		String url = "/jsp-start/start.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		
		String requestedAction = request.getParameter("action");
		//System.out.println("requestedAction: " + requestedAction);
		if(requestedAction == null) {
			//request null - just stay put			
		} else if(requestedAction.equals("query")) {
			//query the database - send the user back to the view (query.jsp)
			url = "/WEB-INF/jsp-user/query.jsp";
			dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);		
			
		} else if(requestedAction.equals("add")) {
			//update the database
			url = "/WEB-INF/jsp-admin/addrecord.jsp";
			dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} else if(requestedAction.equals("newquery")) {
			//query database and display result
			
			//gather select query information
			String queryString = "SELECT ";
			
			//first get the MemberID
			String memberID = request.getParameter("memberID");
			queryString = queryString + memberID + " AS 'ID'";
			
			//get the SELECT values
			String select = "";
			String[] selectCheckboxes = request.getParameterValues("select");
			if (selectCheckboxes != null)
			{
				//first one gets no comma
				select = selectCheckboxes[0];
				
				for (int i = 0; i < selectCheckboxes.length; ++i)
				{
					//then the rest
					select = selectCheckboxes[i];
					queryString = queryString + ", " + select;
	
					if(select.equals("firstName")) {
						queryString = queryString + " AS 'First Name'";
					} else if(select.equals("lastName")) {
						queryString = queryString + " AS 'Last Name'";
					} else if(select.equals("address")) {
						queryString = queryString + " AS 'Address'";
					} else if(select.equals("city")) {
						queryString = queryString + " AS 'City'";
					} else if(select.equals("country")) {
						queryString = queryString + " AS 'Country'";
					} else if(select.equals("code")) {
						queryString = queryString + " AS 'Postal Code'";
					} else if(select.equals("phoneNumber")) {
						queryString = queryString + " AS 'Phone Number'";
					} else if(select.equals("email")) {
						queryString = queryString + " AS 'Email'";
					}
				}
			}
			//add FROM
			queryString = queryString + " FROM a00222500_Members ";
			
			//add WHERE
			String where = request.getParameter("where");
			if(!(where == "")) {
				queryString = queryString + " WHERE " + where;
			}
			
			//add ORDER BY
			String radio = request.getParameter("orderby");
			queryString = queryString + " ORDER BY " + radio;
			
			//add ASC/DESC
			String sort_order = request.getParameter("sort_order");
			queryString = queryString + " " + sort_order;
			
			System.out.println("query:");
			System.out.println(queryString);
			
			db.setQueryString(queryString);
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//set session variables for results page
			session.setAttribute("requestType", "query");
			session.setAttribute("insertStatus", "valid");
			
			//send results to results page
			request.setAttribute("queryString", queryString);
			request.setAttribute("columnNames", headerNames);
			request.setAttribute("results", tableData);

			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp-user/output.jsp");
			dispatcher.forward(request, response);
			
		} else if(requestedAction.equals("addrecord")) {
			//user is requesting to add a new record to the database
			//data is being passed in from addrecord.jsp	
			int maxID = 0;
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String city	 = request.getParameter("city");
			String country = request.getParameter("country");
			String code = request.getParameter("code");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			
			if( ValidateInfo.isValidInput(phoneNumber, REG_PHONE) && 
					ValidateInfo.isValidInput(email, REG_EMAIL) &&
						ValidateInfo.isValidInput(firstName, REG_STRING) &&
							ValidateInfo.isValidInput(lastName, REG_STRING) &&
								ValidateInfo.isValidInput(city, REG_STRING) &&
									ValidateInfo.isValidInput(country, REG_STRING) ) {
				System.out.println("valid");
				
				maxID = db.getMaxID();
				maxID++;
				
				System.out.println("servlet");
				System.out.println(maxID);
				
				String query = "INSERT INTO a00222500_Members VALUES " +
								"('" + firstName + "'," +
								"'" + lastName + "'," +
								"'" + address + "'," +
								"'" + city + "'," +
								"'" + country + "'," +
								"'" + code + "'," +
								"'" + phoneNumber + "'," +
								"'" + email + "')";
		
				//String query = "DROP TABLE a00222500_Members";
				
				db.insertRecord(query);		
			
				//display output to show that record has been created
				String queryString = "SELECT * FROM a00222500_Members WHERE memberID = " + maxID;
				System.out.println(queryString);
				
				db.setQueryString(queryString);
				@SuppressWarnings("rawtypes")
				Vector tableData = db.runQuery();
				
				//display headers
				@SuppressWarnings("rawtypes")
				Vector headerNames = null;
				try {
					headerNames = db.generateMetaData();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//set session variables for results page
				session.setAttribute("requestType", "insert");
				session.setAttribute("insertStatus", "valid");
				
				//send results to results page
				request.setAttribute("queryString", queryString);
				request.setAttribute("columnNames", headerNames);
				request.setAttribute("results", tableData);

				dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp-user/output.jsp");
				dispatcher.forward(request, response);
			
			} else {
				session.setAttribute("requestType", "insert");
				System.out.println("invalid");
				//display message saying that input was invalid
				session.setAttribute("insertStatus", "invalid");
				dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp-user/output.jsp");
				dispatcher.forward(request, response);
			}
			
			
		} else if(requestedAction.equals("modify")) {
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			System.out.println("modify " + memberID);
			
			//get and display the record indicated by the memberID
			String queryString = "SELECT * FROM a00222500_Members WHERE memberID = " + memberID;
			
			//query database
			db.setQueryString(queryString);
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//send results to results page
			request.setAttribute("queryString", queryString);
			request.setAttribute("columnNames", headerNames);
			request.setAttribute("results", tableData);

			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp-admin/update.jsp");
			dispatcher.forward(request, response);
			
			
		} else if(requestedAction.equals("updaterecord")) {
			int id = Integer.parseInt(request.getParameter("memberID"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String code = request.getParameter("code");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			
			//tuck away into database
			db.updateRecord(id, firstName, lastName, address, city, country, code, phoneNumber, email);			
			
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//set session variables for results page
			session.setAttribute("requestType", "update");
			session.setAttribute("insertStatus", "valid");
			
			//send results to results page
			request.setAttribute("columnNames", headerNames);
			request.setAttribute("results", tableData);

			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp-user/output.jsp");
			dispatcher.forward(request, response);
			
		} else if(requestedAction.equals("delete")) {
			int memberID = Integer.parseInt(request.getParameter("memberID"));
			System.out.println("delete " + memberID);
			//delete the record indicated by the memberID
			db.deleteRecord(memberID);
			
			@SuppressWarnings("rawtypes")
			Vector tableData = db.runQuery();
			
			//display headers
			@SuppressWarnings("rawtypes")
			Vector headerNames = null;
			try {
				headerNames = db.generateMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//send results to results page
			session.setAttribute("requestType", "delete");
			session.setAttribute("insertStatus", "valid");
			
			//send results to results page
			request.setAttribute("columnNames", headerNames);
			request.setAttribute("results", tableData);

			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp-user/output.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
