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

/**
 * Servlet implementation class ChangeUserDataByAdmin
 */
@WebServlet("/ChangeUserDataByAdmin")
public class ChangeUserDataByAdmin extends HttpServlet {
	
	private final String EMPLOYEE = "employee";

       
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
	

		HttpSession session = req.getSession();
	
		
		
		
		Employee employee = (Employee) session.getAttribute(EMPLOYEE);
		System.out.println(employee);
			
		String fName= req.getParameter("firstName");
		System.out.println(fName);
		
		String lName= req.getParameter("lastName");
		System.out.println(lName);
		
		String email= req.getParameter("email");
		System.out.println(email);
		
		String role= req.getParameter("role");
		System.out.println(role);
		
		session.setAttribute(EMPLOYEE, employee);
	
		if(true){
			jump("scssfulAddingDataByAdmin.jsp",req,res);
		}else {
			jumpError("errAddingDataByAdmin.jsp","not all field filled",req,res);
		}
	
		
	}
		
	private void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			
			rd.forward(req, resp);
			
		}
	private void jumpError( String url, String cause, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}
	
}
