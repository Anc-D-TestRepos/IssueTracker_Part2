package org.training.issueTracker.web.controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.JDBC.MD5Hashing;
import org.training.issueTracker.service.DAO.exceptions.DAOException;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/Authentification")
public class Authentification extends HttpServlet {
	private final String EMAIL = "email";
	private final String PASS = "pass"; 
	private final String ROLE = "role"; 
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String ADMIN = "admin";
	private final String USER = "user";
	private final String ADMIN_CONTROLLER = "/AuthorizedAdmin";
	private final String USER_CONTROLLER = "/AuthorizedUser";
	private final String LOGIN_ERROR_PAGE = "/errorLoginPage.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	
	protected void performTask( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	
		
		HttpSession session = req.getSession() ;
		DAOInterface employeeSearcher = new DBImplDAO();
		Employee employee  = null;
		MD5Hashing hashKey = new MD5Hashing();
		boolean isValidPass = false;
		
		String enteredEmail;
		String enteredPassword;
		enteredEmail = (String)req.getAttribute(EMAIL);
		
		try {
			enteredPassword = hashKey.getHash((String)req.getAttribute(PASS));
			
			employee = employeeSearcher.getEmployee(enteredEmail);
			
		} catch (DAOException | SAXException e ) {
			
			session.setAttribute("cause", e.getMessage());
			jump(DAO_ERROR_PAGE, req, resp);
			return;
		}
		String firstName ;
		String lastName ;
		String role ;
		
		if(employee != null){
			isValidPass = (employee.getPassword()).equals(enteredPassword);
			firstName = employee.getFirstName();
			lastName = employee.getLastName();
			role = employee.getRole();
			
			session.setAttribute(FIRST_NAME, firstName);
			session.setAttribute(LAST_NAME, lastName);
			session.setAttribute(EMAIL, enteredEmail);
			session.setAttribute(ROLE, role);
			
			if((ADMIN.equals(employee.getRole()))&isValidPass){
				
				jump(ADMIN_CONTROLLER, req, resp);
				
			}else  {
				if((USER.equals(employee.getRole()))&isValidPass){
				
				jump(USER_CONTROLLER, req, resp);
				}else {
					jump(LOGIN_ERROR_PAGE, req, resp);
				}
			}
		}else{
			
			jump(LOGIN_ERROR_PAGE, req, resp);
		}
		
	}	
	
	
	protected void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}
	
}
