package org.training.issueTracker.web.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.DAO.JDBC.MD5Hashing;
import org.training.issueTracker.service.DAO.exceptions.DAOException;

/**
 * Servlet implementation class changeUserPassByAdmin
 */
@WebServlet("/ChangeUserPassByAdmin")
public class ChangeUserPassByAdmin extends HttpServlet {
	private final String  DEFECT_LIST = "defectList";
	private final String EMPLOYEE = "employee";
	private final String  IO_ERR = "I\\O error in AuthorizedAdmin - ";
	private final String ADMIN_PAGE ="/autorizedAdminPage.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	
	private final String LOG_ATRR = "log4";
	private Logger logger ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserPassByAdmin() {
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
	System.out.println("Hello from  changeUserPassByAdmin///////////////////////////////////////////////////////");

		HttpSession session = req.getSession();
		
		logger = (Logger)getServletContext().getAttribute(LOG_ATRR);
		MD5Hashing hashKey = new MD5Hashing();
		String enteredPassword;
		
		DAOInterface defectSearcher = new DBImplDAO();
		//boolean hasDefect = (boolean)session.getAttribute(HAS_DEFECT);
		Employee employee = (Employee) session.getAttribute(EMPLOYEE);
		System.out.println(employee);
			
		String newPass= req.getParameter("newPass");
		System.out.println(newPass);
		
		String confirmPass= req.getParameter("confirmPass");
		System.out.println(confirmPass);
		
		try {
			enteredPassword = hashKey.getHash(confirmPass);
		} catch (DAOException e) {
			session.setAttribute("cause", e.getMessage());
			jump(DAO_ERROR_PAGE, req, res);
			return;
		}
		
		System.out.println("hash -  "+enteredPassword);
		
		
		
		session.setAttribute(EMPLOYEE, employee);
		
	
		if(true){
			jump("/scssfulAddingDataByAdmin.jsp",req,res);
		}else {
			jumpError("/errAddingDataByAdmin.jsp","not all field filled",req,res);
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
