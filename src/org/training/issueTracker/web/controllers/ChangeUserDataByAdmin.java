package org.training.issueTracker.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

/**
 * Servlet implementation class ChangeUserDataByAdmin
 */
@WebServlet("/ChangeUserDataByAdmin")
public class ChangeUserDataByAdmin extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private final String USER ="user";
	private final String CAUSE = "cause";
	private final String SCSFL_PAGE = "/scssfulAddingData.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserDataByAdmin() {
        super();
        // TODO Auto-generated constructor stub
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

	
	protected void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Hello from UserDataEditingByAdminController");

		HttpSession session = req.getSession();
				
		
		String firstName = null;
		String lastName = null;
		String role = null;
		String email = null;
		Employee user = null;
		DAOInterface dataSetter = new DBImplDAO();
		
		try {
			
			firstName = (String)req.getParameter("first_name");		
			lastName = (String)req.getParameter("last_name");		
			role = (String)req.getParameter("role");		
			email = (String)req.getParameter("email");		
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(role);
			System.out.println(email);
			user = new Employee();
			
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setRole(role);
			user.setEmail(email);
			System.out.println(user.toString());
				
			dataSetter.replaceUserDataByAdmin(user);
			
			
						
		} catch (DAOException | ClassNotFoundException e) {
			session.setAttribute(CAUSE, "problem with connection to database");
			jump(DAO_ERROR_PAGE,req,res);
			return;
		}
			jump(SCSFL_PAGE,req,res);
		
	
		
	}
		
	private void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}
	
}
